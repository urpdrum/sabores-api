package guia.saboresapi.application.reserva;

import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.exception.reserva.ReservaNotFoundException;
import guia.saboresapi.domain.input.reserva.AtualizarReservaRequest;
import guia.saboresapi.domain.mapper.reserva.ReservaMapper;
import guia.saboresapi.domain.usecase.reserva.AtualizarReservaUseCase;
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


import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AtualizarReservaControllerTest {

    @Mock
    private AtualizarReservaUseCase atualizarReservaUseCase;

    @Mock
    private ReservaMapper mapper;

    private MockMvc mockMvc;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        AtualizarReservaController controller = new AtualizarReservaController(atualizarReservaUseCase, mapper);
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
    void devePermitirAtualizarReserva() throws Exception {
        var id = 1L;
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarAtualizarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        when(atualizarReservaUseCase.atualizarReserva(anyLong(), any(Reserva.class))).thenReturn(reserva);
        when(mapper.toReserva(any(AtualizarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(put("/reservas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservaId").value(id))
                .andExpect(jsonPath("$.status").value(response.status().toString()))
                .andExpect(jsonPath("$.usuarioId").value(response.usuarioId()))
                .andExpect(jsonPath("$.mesaId").value(response.mesaId()))
                .andExpect(jsonPath("$.dataInicio").exists())
                .andExpect(jsonPath("$.dataFim").exists());

        verify(mapper, times(1)).toReserva(any(AtualizarReservaRequest.class));
        verify(atualizarReservaUseCase, times(1)).atualizarReserva(anyLong(), any(Reserva.class));
        verify(mapper, times(1)).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_IdNaoEncontrado() throws Exception {
        var id = 1L;
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarAtualizarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        var mensagemException = "Reserva de id: " + id + " não encontrada.";
        when(atualizarReservaUseCase.atualizarReserva(anyLong(), any(Reserva.class))).thenThrow(new ReservaNotFoundException(mensagemException));
        when(mapper.toReserva(any(AtualizarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(put("/reservas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas/" + id));

        verify(mapper, times(1)).toReserva(any(AtualizarReservaRequest.class));
        verify(atualizarReservaUseCase, times(1)).atualizarReserva(anyLong(), any(Reserva.class));
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataInicioAntesDeHoje() throws Exception {
        var id = 1L;
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarAtualizarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        reserva.setDataInicio(LocalDateTime.now().minusDays(1));
        var mensagemException = "Só é possível reservar para datas futuras.";
        when(atualizarReservaUseCase.atualizarReserva(anyLong(), any(Reserva.class))).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toReserva(any(AtualizarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(put("/reservas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas/" + id));

        verify(mapper, times(1)).toReserva(any(AtualizarReservaRequest.class));
        verify(atualizarReservaUseCase, times(1)).atualizarReserva(anyLong(), any(Reserva.class));
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataFimAntesDeHoje() throws Exception {
        var id = 1L;
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarAtualizarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        reserva.setDataFim(LocalDateTime.now().minusDays(1));
        var mensagemException = "Só é possível reservar para datas futuras.";
        when(atualizarReservaUseCase.atualizarReserva(anyLong(), any(Reserva.class))).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toReserva(any(AtualizarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(put("/reservas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas/" + id));

        verify(mapper, times(1)).toReserva(any(AtualizarReservaRequest.class));
        verify(atualizarReservaUseCase, times(1)).atualizarReserva(anyLong(), any(Reserva.class));
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataInicioMaiorQueDataFim() throws Exception {
        var id = 1L;
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarAtualizarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        reserva.setDataInicio(LocalDateTime.now().plusDays(2));
        reserva.setDataFim(LocalDateTime.now().plusDays(1));
        var mensagemException = "A Data inicio da reserva deve ser anterior a data fim.";
        when(atualizarReservaUseCase.atualizarReserva(anyLong(), any(Reserva.class))).thenThrow(new IllegalArgumentException(mensagemException));
        when(mapper.toReserva(any(AtualizarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(put("/reservas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas/" + id));

        verify(mapper, times(1)).toReserva(any(AtualizarReservaRequest.class));
        verify(atualizarReservaUseCase, times(1)).atualizarReserva(anyLong(), any(Reserva.class));
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_MesaJaReservada() throws Exception {
        var id = 1L;
        var reserva = ReservaHelper.gerarReserva();
        var request = ReservaHelper.gerarAtualizarReservaRequest();
        var response = ReservaHelper.gerarReservaResponse();
        var mensagemException = "A Mesa de id: " + reserva.getMesa().getMesaId() + " já está reservada no período selecionado.";
        when(atualizarReservaUseCase.atualizarReserva(anyLong(), any(Reserva.class))).thenThrow(new IllegalStateException(mensagemException));
        when(mapper.toReserva(any(AtualizarReservaRequest.class))).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(put("/reservas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStringHelper.asJsonString(request)))
                
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas/" + id));

        verify(mapper, times(1)).toReserva(any(AtualizarReservaRequest.class));
        verify(atualizarReservaUseCase, times(1)).atualizarReserva(anyLong(), any(Reserva.class));
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }
}
