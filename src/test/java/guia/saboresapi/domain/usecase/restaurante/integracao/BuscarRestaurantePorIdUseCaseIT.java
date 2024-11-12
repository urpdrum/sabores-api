package guia.saboresapi.domain.usecase.restaurante.integracao;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorIdUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BuscarRestaurantePorIdUseCaseIT {

    @Autowired
    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;


    @Test
    void devePermitirBuscarRestaurantePorId() {
        var id = 1L;

        var restauranteObtido = buscarRestaurantePorIdUseCase.buscarRestaurantePorId(id);

        assertThat(restauranteObtido)
                .isNotNull()
                .isInstanceOf(Restaurante.class);
        assertThat(restauranteObtido.getRestauranteId()).isEqualTo(id);
    }

    @Test
    void deveGerarExcecao_QuandoBuscarRestaurantePorId_IdNaoEncontrado() {
        var id = 1123456L;
        var mensagemException = "Restaurante de id: " + id + " não encontrado.";

        assertThatThrownBy(() -> buscarRestaurantePorIdUseCase.buscarRestaurantePorId(id))
                .isInstanceOf(RestauranteNotFoundException.class)
                .hasMessage(mensagemException);
    }
}
