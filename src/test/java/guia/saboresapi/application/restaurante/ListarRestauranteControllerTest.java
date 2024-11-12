package guia.saboresapi.application.restaurante;


import com.fiap.tc.restaurantes.domain.entity.Restaurante;
import com.fiap.tc.restaurantes.domain.mapper.restaurante.RestauranteMapper;
import com.fiap.tc.restaurantes.domain.output.restaurante.RestauranteResponse;
import com.fiap.tc.restaurantes.domain.usecase.restaurante.ListarRestauranteUseCase;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ListarRestauranteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ListarRestauranteUseCase listarRestauranteUseCase;

    @Mock
    private RestauranteMapper restauranteMapper;


    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ListarRestauranteController controller = new ListarRestauranteController(restauranteMapper, listarRestauranteUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirListarRestaurantes() throws Exception {
        // Arrange
        Restaurante entidade1 = RestauranteHelper.gerarRestauranteValido();
        Restaurante entidade2 = RestauranteHelper.gerarRestauranteValido();

        List<Restaurante> restaurantes = Arrays.asList(entidade1, entidade2);

        RestauranteResponse restauranteResponse1 = RestauranteHelper.gerarRestauranteResponse();
        RestauranteResponse restauranteResponse2 = RestauranteHelper.gerarRestauranteResponse();
        List<RestauranteResponse> restaurantesResponse = Arrays.asList(restauranteResponse1, restauranteResponse2);

        when(listarRestauranteUseCase.listarRestaurantes()).thenReturn(restaurantes);
        when(restauranteMapper.toRestauranteResponse(any(Restaurante.class))).thenReturn(restauranteResponse1, restauranteResponse2);

        // Act
        mockMvc.perform(get("/restaurantes")
                        .content(JsonStringHelper.asJsonString(restaurantesResponse))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(restaurantesResponse.size()));

        verify(listarRestauranteUseCase, times(1)).listarRestaurantes();
    }
}