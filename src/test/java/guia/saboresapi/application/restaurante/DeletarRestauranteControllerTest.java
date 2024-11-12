package guia.saboresapi.application.restaurante;



import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.mapper.restaurante.RestauranteMapper;
import guia.saboresapi.domain.usecase.restaurante.DeletarRestauranteUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class DeletarRestauranteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DeletarRestauranteUseCase deletarRestauranteUseCase;

    @Mock
    private RestauranteMapper restauranteMapper;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        DeletarRestauranteController controller = new DeletarRestauranteController(restauranteMapper, deletarRestauranteUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirDeletarRestaurante() throws Exception {
        // Arrange
        Long id = 1L;
        when(deletarRestauranteUseCase.deletarRestaurante(any(Long.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/restaurantes/{id}", id))
                
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(deletarRestauranteUseCase, times(1)).deletarRestaurante(id);
    }

    @Test
    void deveGerarExcecao_QuandoDeletarRestaurante_IdNaoEncontrado() throws Exception {
        var id = 123456L;
        var mensagemException = "Restaurante de id: " + id + " não encontrado.";
        when(deletarRestauranteUseCase.deletarRestaurante(anyLong())).thenThrow(new RestauranteNotFoundException(mensagemException));

        mockMvc.perform(delete("/restaurantes/{id}", id))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/restaurantes/" + id));
        verify(deletarRestauranteUseCase, times(1)).deletarRestaurante(id);
    }
}