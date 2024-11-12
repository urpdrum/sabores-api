package guia.saboresapi.application.reserva;

import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.exception.reserva.ReservaNotFoundException;
import guia.saboresapi.domain.usecase.reserva.DeletarReservaUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class DeletarReservaControllerTest {

    @Mock
    private DeletarReservaUseCase deletarReservaUseCase;

    private MockMvc mockMvc;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        DeletarReservaController controller = new DeletarReservaController(deletarReservaUseCase);
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
    void devePermitirDeletarReserva() throws Exception {
        var id = 1L;
        when(deletarReservaUseCase.deletarReserva(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/reservas/{id}", id))
                
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        verify(deletarReservaUseCase, times(1)).deletarReserva(anyLong());
    }

    @Test
    void deveGerarExcecao_QuandoDeletarReserva_IdNaoEncontrado() throws Exception {
        var id = 1L;
        var mensagemException = "Reserva de id: " + id + " não encontrada.";
        when(deletarReservaUseCase.deletarReserva(anyLong())).thenThrow(new ReservaNotFoundException(mensagemException));

        mockMvc.perform(delete("/reservas/{id}", id))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/reservas/" + id));
        verify(deletarReservaUseCase, times(1)).deletarReserva(anyLong());
    }

}
