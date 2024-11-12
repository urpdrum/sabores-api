package guia.saboresapi.domain.usecase.restaurante;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorIdInterface;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BuscarRestaurantePorIdUseCaseTest {

    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    @Mock
    private BuscarRestaurantePorIdInterface buscarRestaurantePorIdInterface;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        buscarRestaurantePorIdUseCase = new BuscarRestaurantePorIdUseCase(buscarRestaurantePorIdInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirBuscarRestaurantePorId() {
        var id = 1L;
        var restaurante = RestauranteHelper.gerarRestauranteValido();
        when(buscarRestaurantePorIdInterface.buscarRestaurantePorId(anyLong())).thenReturn(restaurante);

        var restauranteObtido = buscarRestaurantePorIdUseCase.buscarRestaurantePorId(id);

        assertThat(restauranteObtido)
                .isNotNull()
                .isInstanceOf(Restaurante.class);
        assertThat(restauranteObtido.getRestauranteId()).isEqualTo(id);
        verify(buscarRestaurantePorIdInterface, times(1)).buscarRestaurantePorId(anyLong());
    }

    @Test
    void deveGerarExcecao_QuandoBuscarRestaurantePorId_IdNaoEncontrado() {
        var id = 1L;
        var mensagemException = "Restaurante de id: " + id + " não encontrado.";
        when(buscarRestaurantePorIdInterface.buscarRestaurantePorId(anyLong())).thenReturn(null);


        assertThatThrownBy(() -> buscarRestaurantePorIdUseCase.buscarRestaurantePorId(id))
                .isInstanceOf(RestauranteNotFoundException.class)
                .hasMessage(mensagemException);

        verify(buscarRestaurantePorIdInterface, times(1)).buscarRestaurantePorId(anyLong());
    }
}
