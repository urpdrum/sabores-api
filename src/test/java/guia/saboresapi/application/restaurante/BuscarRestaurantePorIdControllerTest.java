package guia.saboresapi.application.restaurante;


import guia.saboresapi.application.handler.GlobalExceptionHandler;
import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.mapper.restaurante.RestauranteMapper;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorIdUseCase;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
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
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BuscarRestaurantePorIdControllerTest {

    @Mock
    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    private MockMvc mockMvc;

    @Mock
    private RestauranteMapper mapper;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        BuscarRestaurantePorIdController controller = new BuscarRestaurantePorIdController(buscarRestaurantePorIdUseCase, mapper);
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
    void devePermitirBuscarRestaurantePorId() throws Exception {
        var id = 1L;
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        var response = RestauranteHelper.gerarRestauranteResponse();
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(restaurante);
        when(mapper.toRestauranteResponse(any(Restaurante.class))).thenReturn(response);

        mockMvc.perform(get("/restaurantes/{id}", id))
                
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restauranteId").value(id))
                .andExpect(jsonPath("$.nome").value(response.nome()))
                .andExpect(jsonPath("$.endereco.logradouro").value(response.endereco().logradouro()))
                .andExpect(jsonPath("$.tipoDeCozinha").value(response.tipoDeCozinha()))
                .andExpect(jsonPath("$.capacidade").value(response.capacidade()))
                .andExpect(jsonPath("$.horarioFuncionamento").value(response.horarioFuncionamento()));

        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(mapper, times(1)).toRestauranteResponse(any(Restaurante.class));
    }

    @Test
    void deveGerarExcecao_QuandoBuscarRestaurantePorId_IdNaoEncontrado() throws Exception {
        var id = 1L;
        var response = RestauranteHelper.gerarRestauranteResponse();
        var mensagemException = "Restaurante de id: " + id + " não encontrado.";
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenThrow(new RestauranteNotFoundException(mensagemException));
        when(mapper.toRestauranteResponse(any(Restaurante.class))).thenReturn(response);

        mockMvc.perform(get("/restaurantes/{id}", id))
                
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value(mensagemException))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.horario").exists())
                .andExpect(jsonPath("$.rota").value("/restaurantes/" + id));

        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(mapper, never()).toRestauranteResponse(any(Restaurante.class));
    }
}
