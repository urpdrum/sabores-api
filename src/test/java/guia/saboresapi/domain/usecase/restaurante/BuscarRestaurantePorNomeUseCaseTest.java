package guia.saboresapi.domain.usecase.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorNomeInterface;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BuscarRestaurantePorNomeUseCaseTest {

    private BuscarRestaurantePorNomeUseCase useCase;

    @Mock
    private BuscarRestaurantePorNomeInterface buscarRestaurantePorNomeInterface;

    AutoCloseable openMocks;


    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarRestaurantePorNomeUseCase(buscarRestaurantePorNomeInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirbuscarRestaurantePorLocalidade() {
        // Arrange
        Restaurante entidade1 = RestauranteHelper.gerarRestauranteValido();
        Restaurante entidade2 = RestauranteHelper.gerarRestauranteValido();

        String nome = "Nome Teste";

        List<Restaurante> restaurantes = Arrays.asList(entidade1, entidade2);

        when(buscarRestaurantePorNomeInterface.buscarRestaurantePorNome(any())).thenReturn(restaurantes);

        //Act
        List<Restaurante> resultado = useCase.buscarRestaurantePorNome(nome);

        // Assert
        assertThat(resultado)
                .hasSize(2)
                .containsExactlyInAnyOrder(entidade1, entidade2);

        assertThat(resultado.get(0).getNome())
                .isEqualTo(entidade1.getNome());

        assertThat(resultado.get(1).getNome())
                .isEqualTo(entidade1.getNome());

        verify(buscarRestaurantePorNomeInterface, times(1)).buscarRestaurantePorNome(any());
    }
}