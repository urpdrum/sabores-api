package guia.saboresapi.application.reserva;

import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.input.reserva.CadastrarReservaRequest;
import guia.saboresapi.domain.mapper.reserva.ReservaMapper;
import guia.saboresapi.domain.usecase.reserva.CadastrarReservaUseCase;
import guia.saboresapi.utils.generic.JsonStringHelper;
import guia.saboresapi.utils.reserva.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CadastrarReservaControllerTest {

    @Mock
    private CadastrarReservaUseCase cadastrarReservaUseCase;

    @Mock
    private ReservaMapper mapper;

    private MockMvc mockMvc;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        CadastrarReservaController controller = new CadastrarReservaController(cadastrarReservaUseCase, mapper);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirCadastrarReserva() throws Exception {
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarCadastrarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        when(cadastrarReservaUseCase.cadastrarReserva(any(Reserva.class), anyLong(), anyLong())).thenReturn(reserva);
        when(mapper.toReserva(any(CadastrarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(response.status().toString()))
                .andExpect(jsonPath("$.usuarioId").value(response.usuarioId()))
                .andExpect(jsonPath("$.mesaId").value(response.mesaId()))
                .andExpect(jsonPath("$.dataInicio").exists())
                .andExpect(jsonPath("$.dataFim").exists());
        verify(mapper, times(1)).toReserva(any(CadastrarReservaRequest.class));
        verify(cadastrarReservaUseCase, times(1)).cadastrarReserva(any(Reserva.class), anyLong(), anyLong());
        verify(mapper, times(1)).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_UsuarioNaoEncontrado() throws Exception {
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarCadastrarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        var mensagemException = "Usuário de id: " + reserva.getUsuario().getUsuarioId() + " não encontrado.";
        when(cadastrarReservaUseCase.cadastrarReserva(any(Reserva.class), anyLong(), anyLong())).thenThrow(new UsuarioNotFoundException(mensagemException));
        when(mapper.toReserva(any(CadastrarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas" ));
        verify(mapper, times(1)).toReserva(any(CadastrarReservaRequest.class));
        verify(cadastrarReservaUseCase, times(1)).cadastrarReserva(any(Reserva.class), anyLong(), anyLong());
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_MesaNaoEncontrada() throws Exception {
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarCadastrarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        var mensagemException = "Mesa de id: " + reserva.getMesa().getMesaId() + " não encontrada";
        when(cadastrarReservaUseCase.cadastrarReserva(any(Reserva.class), anyLong(), anyLong())).thenThrow(new MesaNotFoundException(mensagemException));
        when(mapper.toReserva(any(CadastrarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas" ));
        verify(mapper, times(1)).toReserva(any(CadastrarReservaRequest.class));
        verify(cadastrarReservaUseCase, times(1)).cadastrarReserva(any(Reserva.class), anyLong(), anyLong());
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataInicioAntesDeHoje() throws Exception {
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarCadastrarReservaRequestComDataInicioAntesDeHoje();
        var response = ReservaHelper.gerarReservaResponse();
        var mensagemException = "Só é possível reservar para datas futuras.";
        when(cadastrarReservaUseCase.cadastrarReserva(any(Reserva.class), anyLong(), anyLong())).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toReserva(any(CadastrarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas" ));
        verify(mapper, times(1)).toReserva(any(CadastrarReservaRequest.class));
        verify(cadastrarReservaUseCase, times(1)).cadastrarReserva(any(Reserva.class), anyLong(), anyLong());
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataFimAntesDeHoje() throws Exception {
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarCadastrarReservaRequestComDataFimAntesDeHoje();
        var response = ReservaHelper.gerarReservaResponse();
        var mensagemException = "Só é possível reservar para datas futuras.";
        when(cadastrarReservaUseCase.cadastrarReserva(any(Reserva.class), anyLong(), anyLong())).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toReserva(any(CadastrarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas" ));
        verify(mapper, times(1)).toReserva(any(CadastrarReservaRequest.class));
        verify(cadastrarReservaUseCase, times(1)).cadastrarReserva(any(Reserva.class), anyLong(), anyLong());
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataInicioMaiorQueDataFim() throws Exception {
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarCadastrarReservaRequestComDataFimAntesDeDataInicio();
        var response = ReservaHelper.gerarReservaResponse();
        var mensagemException = "A Data inicio da reserva deve ser anterior a data fim.";
        when(cadastrarReservaUseCase.cadastrarReserva(any(Reserva.class), anyLong(), anyLong())).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toReserva(any(CadastrarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas" ));
        verify(mapper, times(1)).toReserva(any(CadastrarReservaRequest.class));
        verify(cadastrarReservaUseCase, times(1)).cadastrarReserva(any(Reserva.class), anyLong(), anyLong());
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_MesaJaReservada() throws Exception {
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarCadastrarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        var mensagemException = "A Mesa de id: " + reserva.getMesa().getMesaId() + " já está reservada no período selecionado.";
        when(cadastrarReservaUseCase.cadastrarReserva(any(Reserva.class), anyLong(), anyLong())).thenThrow(new IllegalStateException(mensagemException));
        when(mapper.toReserva(any(CadastrarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas" ));
        verify(mapper, times(1)).toReserva(any(CadastrarReservaRequest.class));
        verify(cadastrarReservaUseCase, times(1)).cadastrarReserva(any(Reserva.class), anyLong(), anyLong());
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }
}
