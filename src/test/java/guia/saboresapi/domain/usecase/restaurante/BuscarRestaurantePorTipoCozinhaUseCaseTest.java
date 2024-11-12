package guia.saboresapi.domain.usecase.restaurante;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorTipoCozinhaInterface;
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

class BuscarRestaurantePorTipoCozinhaUseCaseTest {

    private BuscarRestaurantePorTipoCozinhaUseCase useCase;

    @Mock
    private BuscarRestaurantePorTipoCozinhaInterface buscarRestaurantePorTipoCozinhaInterface;

    AutoCloseable openMocks;


    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarRestaurantePorTipoCozinhaUseCase(buscarRestaurantePorTipoCozinhaInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirbuscarRestaurantePorTipoCozinha() {
        // Arrange
        Restaurante entidade1 = RestauranteHelper.gerarRestauranteValido();
        Restaurante entidade2 = RestauranteHelper.gerarRestauranteValido();

        List<Restaurante> restaurantes = Arrays.asList(entidade1, entidade2);

        when(buscarRestaurantePorTipoCozinhaInterface.buscarPorTipoCozinha(any())).thenReturn(restaurantes);

        //Act
        List<Restaurante> resultado = useCase.buscarPorTipoCozinha("MEXICANA");

        // Assert
        assertThat(resultado)
                .hasSize(2)
                .containsExactlyInAnyOrder(entidade1, entidade2);

        assertThat(resultado.get(0).getTipoDeCozinha())
                .isEqualTo(entidade1.getTipoDeCozinha());

        assertThat(resultado.get(1).getTipoDeCozinha())
                .isEqualTo(entidade1.getTipoDeCozinha());

        verify(buscarRestaurantePorTipoCozinhaInterface, times(1)).buscarPorTipoCozinha(any());
    }
}