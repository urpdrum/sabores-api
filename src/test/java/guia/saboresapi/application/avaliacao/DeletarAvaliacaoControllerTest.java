package guia.saboresapi.application.avaliacao;

import guia.saboresapi.application.avaliacao.DeletarAvaliacaoController;
import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.exception.avaliacao.AvaliacaoNotFoundException;
import guia.saboresapi.domain.usecase.avaliacao.DeletarAvaliacaoUseCase;
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

class DeletarAvaliacaoControllerTest {

    @Mock
    private DeletarAvaliacaoUseCase deletarAvaliacaoUseCase;

    private MockMvc mockMvc;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        DeletarAvaliacaoController controller = new DeletarAvaliacaoController(deletarAvaliacaoUseCase);
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
    void devePermitirDeletarAvaliacao() throws Exception {
        var id = 1L;
        when(deletarAvaliacaoUseCase.deletarAvaliacao(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/avaliacoes/{id}", id))
                
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        verify(deletarAvaliacaoUseCase, times(1)).deletarAvaliacao(anyLong());
    }

    @Test
    void deveGerarExcecao_QuandoDeletarAvaliacao_IdNaoEncontrado() throws Exception {
        var id = 1L;
        var mensagemException = "Avaliação de id: " + id + " não encontrada.";
        when(deletarAvaliacaoUseCase.deletarAvaliacao(anyLong())).thenThrow(new AvaliacaoNotFoundException(mensagemException));

        mockMvc.perform(delete("/avaliacoes/{id}", id))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/avaliacoes/" + id));
        verify(deletarAvaliacaoUseCase, times(1)).deletarAvaliacao(anyLong());
    }
}
