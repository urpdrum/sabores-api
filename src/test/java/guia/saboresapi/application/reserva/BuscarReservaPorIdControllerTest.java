package guia.saboresapi.application.reserva;

import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.exception.reserva.ReservaNotFoundException;
import guia.saboresapi.domain.mapper.reserva.ReservaMapper;
import guia.saboresapi.domain.usecase.reserva.BuscarReservaPorIdUseCase;
import guia.saboresapi.utils.reserva.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BuscarReservaPorIdControllerTest {

    @Mock
    private BuscarReservaPorIdUseCase buscarReservaPorIdUseCase;

    @Mock
    private ReservaMapper mapper;

    private MockMvc mockMvc;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        BuscarReservaPorIdController controller = new BuscarReservaPorIdController(buscarReservaPorIdUseCase, mapper);
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
    void devePermitirBuscarReservaPorId() throws Exception {
        var id = 1L;
        var reserva = ReservaHelper.gerarReserva();
        var response = ReservaHelper.gerarReservaResponse();
        when(buscarReservaPorIdUseCase.buscarReservaPorId(anyLong())).thenReturn(reserva);
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(get("/reservas/{id}", id))
                
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservaId").value(id))
                .andExpect(jsonPath("$.status").value(response.status().toString()))
                .andExpect(jsonPath("$.usuarioId").value(response.usuarioId()))
                .andExpect(jsonPath("$.mesaId").value(response.mesaId()))
                .andExpect(jsonPath("$.dataInicio").exists())
                .andExpect(jsonPath("$.dataFim").exists());
        verify(buscarReservaPorIdUseCase, times(1)).buscarReservaPorId(anyLong());
        verify(mapper, times(1)).toReservaResponse(any(Reserva.class));
    }

    @Test
    void deveGerarExcecao_QuandoBuscarReservaPorId_IdNaoEncontrado() throws Exception {
        var id = 1L;
        var response = ReservaHelper.gerarReservaResponse();
        var mensagemException = "Reserva de id: " + id + " não encontrada.";
        when(buscarReservaPorIdUseCase.buscarReservaPorId(anyLong())).thenThrow(new ReservaNotFoundException(mensagemException));
        when(mapper.toReservaResponse(any(Reserva.class))).thenReturn(response);

        mockMvc.perform(get("/reservas/{id}", id))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas/" + id));
        verify(buscarReservaPorIdUseCase, times(1)).buscarReservaPorId(anyLong());
        verify(mapper, never()).toReservaResponse(any(Reserva.class));
    }
}
