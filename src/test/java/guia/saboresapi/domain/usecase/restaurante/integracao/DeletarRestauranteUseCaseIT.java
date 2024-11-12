package guia.saboresapi.domain.usecase.restaurante.integracao;


import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.usecase.restaurante.DeletarRestauranteUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DeletarRestauranteUseCaseIT {

    @Autowired
    private DeletarRestauranteUseCase deletarRestauranteUseCase;

    @Test
    void devePermitirDeletarRestaurante_ComAvaliacao() {

        var identificador = 8L;

        var restauranteDeletado = deletarRestauranteUseCase.deletarRestaurante(identificador);

        assertThat(restauranteDeletado).isTrue();
    }

    @Test
    void devePermitirDeletarRestaurante_SemAvaliacao() {
        var identificador = 7L;

        var restauranteDeletado = deletarRestauranteUseCase.deletarRestaurante(identificador);

        assertThat(restauranteDeletado).isTrue();
    }

    @Test
    void deveGerarExcecao_QuandoDeletarRestaurante_IdNaoEncontrado() {
        var identificador = 8156415848L;
        var mensagemException = "Restaurante de id: " + identificador + " não encontrado.";

        assertThatThrownBy(() -> deletarRestauranteUseCase.deletarRestaurante(identificador))
                .isInstanceOf(RestauranteNotFoundException.class)
                .hasMessage(mensagemException);
    }
}
