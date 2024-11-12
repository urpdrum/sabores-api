package guia.saboresapi.application.restaurante;

import com.fiap.tc.restaurantes.domain.entity.Restaurante;
import com.fiap.tc.restaurantes.domain.mapper.restaurante.RestauranteMapper;
import com.fiap.tc.restaurantes.domain.output.restaurante.RestauranteResponse;
import com.fiap.tc.restaurantes.domain.usecase.restaurante.BuscarRestaurantePorTipoCozinhaUseCase;
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

class BuscarRestaurantePorTipoCozinhaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BuscarRestaurantePorTipoCozinhaUseCase buscarRestaurantePorTipoCozinhaUseCase;

    @Mock
    private RestauranteMapper restauranteMapper;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        BuscarRestaurantePorTipoCozinhaController controller = new BuscarRestaurantePorTipoCozinhaController(restauranteMapper, buscarRestaurantePorTipoCozinhaUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirBuscarRestaurantePorTipoCozinha() throws Exception {
        // Arrange
        String tipoCozinha = "MEXICANA";
        Restaurante entidade1 = RestauranteHelper.gerarRestauranteValido();
        Restaurante entidade2 = RestauranteHelper.gerarRestauranteValido();

        List<Restaurante> restaurantes = Arrays.asList(entidade1, entidade2);
        RestauranteResponse response = RestauranteHelper.gerarRestauranteResponse();

        when(buscarRestaurantePorTipoCozinhaUseCase.buscarPorTipoCozinha(any(String.class))).thenReturn(restaurantes);
        when(restauranteMapper.toRestauranteResponse(entidade1)).thenReturn(response);

        // Act
        mockMvc.perform(get("/restaurantes/tipo")
                        .content(JsonStringHelper.asJsonString(response))
                        .param("tipoCozinha", tipoCozinha)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                
                .andExpect(status().isOk());

        verify(buscarRestaurantePorTipoCozinhaUseCase, times(1)).buscarPorTipoCozinha(anyString());
        verify(restauranteMapper, times(2)).toRestauranteResponse(any(Restaurante.class));
    }
}