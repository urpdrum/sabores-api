package guia.saboresapi.application.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.mapper.restaurante.RestauranteMapper;
import guia.saboresapi.domain.output.restaurante.RestauranteResponse;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorNomeUseCase;
import guia.saboresapi.utils.generic.JsonStringHelper;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BuscarRestaurantePorNomeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BuscarRestaurantePorNomeUseCase buscarRestaurantePorNomeUseCase;

    @Mock
    private RestauranteMapper restauranteMapper;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        BuscarRestaurantePorNomeController controller = new BuscarRestaurantePorNomeController(restauranteMapper, buscarRestaurantePorNomeUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirBuscarRestaurantePorNome() throws Exception {
        // Arrange
        String nome = "Nome Teste";
        Restaurante entidade1 = RestauranteHelper.gerarRestauranteValido();
        Restaurante entidade2 = RestauranteHelper.gerarRestauranteValido();

        List<Restaurante> restaurantes = Arrays.asList(entidade1, entidade2);
        RestauranteResponse response = RestauranteHelper.gerarRestauranteResponse();

        when(buscarRestaurantePorNomeUseCase.buscarRestaurantePorNome(any(String.class))).thenReturn(restaurantes);
        when(restauranteMapper.toRestauranteResponse(entidade1)).thenReturn(response);

        // Act
        mockMvc.perform(get("/restaurantes/nome")
                .content(JsonStringHelper.asJsonString(response))
                .param("nome", nome)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(buscarRestaurantePorNomeUseCase, times(1)).buscarRestaurantePorNome(anyString());
        verify(restauranteMapper, times(2)).toRestauranteResponse(any(Restaurante.class));
    }
}