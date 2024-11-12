package guia.saboresapi.application.restaurante;

import com.fiap.tc.restaurantes.domain.entity.Restaurante;
import com.fiap.tc.restaurantes.domain.mapper.restaurante.RestauranteMapper;
import com.fiap.tc.restaurantes.domain.output.restaurante.RestauranteResponse;
import com.fiap.tc.restaurantes.domain.usecase.restaurante.BuscarRestaurantePorLocalidadeUseCase;
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

class BuscarRestaurantePorLocalidadeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BuscarRestaurantePorLocalidadeUseCase buscarRestaurantePorLocalidadeUseCase;

    @Mock
    private RestauranteMapper restauranteMapper;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        BuscarRestaurantePorLocalidadeController controller = new BuscarRestaurantePorLocalidadeController(restauranteMapper, buscarRestaurantePorLocalidadeUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirBuscarRestaurantePorLocalidade() throws Exception {
        // Arrange
        String rua = "Rua Teste";
        Restaurante entidade1 = RestauranteHelper.gerarRestauranteValido();
        Restaurante entidade2 = RestauranteHelper.gerarRestauranteValido();

        List<Restaurante> restaurantes = Arrays.asList(entidade1, entidade2);
        RestauranteResponse response = RestauranteHelper.gerarRestauranteResponse();

        when(buscarRestaurantePorLocalidadeUseCase.buscarRestaurantePorLocalidade(any(String.class))).thenReturn(restaurantes);
        when(restauranteMapper.toRestauranteResponse(entidade1)).thenReturn(response);

        // Act
        mockMvc.perform(get("/restaurantes/localidade")
                .content(JsonStringHelper.asJsonString(response))
                .param("localidade", rua)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(buscarRestaurantePorLocalidadeUseCase, times(1)).buscarRestaurantePorLocalidade(anyString());
        verify(restauranteMapper, times(2)).toRestauranteResponse(any(Restaurante.class));
    }
}