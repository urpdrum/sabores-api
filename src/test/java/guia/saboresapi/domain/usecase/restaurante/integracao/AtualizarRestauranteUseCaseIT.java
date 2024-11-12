package guia.saboresapi.domain.usecase.restaurante.integracao;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.usecase.restaurante.AtualizarRestauranteUseCase;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AtualizarRestauranteUseCaseIT {

    @Autowired
    private AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @Test
    void deveAtualizarRestaurante() {
        // Arrange
        Restaurante restaurante = RestauranteHelper.gerarRestauranteValido();
        Long id = 1L;

        // Act
        Restaurante restauranteAtualizado = atualizarRestauranteUseCase.atualizarRestaurante(id, restaurante);

        // Assert
        assertThat(restauranteAtualizado)
                .isNotNull()
                .isInstanceOf(Restaurante.class);

        assertThat(restauranteAtualizado.getNome())
                .isEqualTo(restaurante.getNome());
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_IdNaoEncontrado() {
        Restaurante restaurante = RestauranteHelper.gerarRestauranteValido();
        Long id = 115648122L;
        var mensagemException = "Restaurante de id: " + id + " não encontrado.";

        assertThatThrownBy(() -> atualizarRestauranteUseCase.atualizarRestaurante(id, restaurante))
                .isInstanceOf(RestauranteNotFoundException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_CepNaoEncontrado() {
        Restaurante restaurante = RestauranteHelper.gerarRestauranteValido();
        restaurante.getEndereco().setCep("12345678");
        Long id = 1L;
        var mensagemException = "CEP inexistente.";

        assertThatThrownBy(() -> atualizarRestauranteUseCase.atualizarRestaurante(id, restaurante))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_NomeNaoInformado() {
        Restaurante restaurante = RestauranteHelper.gerarRestauranteValido();
        restaurante.setNome("");
        Long id = 1L;
        var mensagemException = "O nome do restaurante deve ser informado.";

        assertThatThrownBy(() -> atualizarRestauranteUseCase.atualizarRestaurante(id, restaurante))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarRestaurante_CapacidadeNaoInformado() {
        Restaurante restaurante = RestauranteHelper.gerarRestauranteValido();
        restaurante.setCapacidade(null);
        Long id = 1L;
        var mensagemException = "A capacidade do restaurante deve ser informada.";

        assertThatThrownBy(() -> atualizarRestauranteUseCase.atualizarRestaurante(id, restaurante))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(mensagemException);
    }
}
