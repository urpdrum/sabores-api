package guia.saboresapi.domain.usecase.avaliacao.integracao;

import com.fiap.tc.restaurantes.domain.entity.Avaliacao;
import com.fiap.tc.restaurantes.domain.exception.avaliacao.AvaliacaoNotFoundException;
import com.fiap.tc.restaurantes.domain.usecase.avaliacao.AtualizarAvaliacaoUseCase;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class AtualizarAvaliacaoUseCaseIT {

    @Autowired
    private AtualizarAvaliacaoUseCase atualizarAvaliacaoUseCase;

    @Test
    void devePermitirAtualizarAvaliacao() {
        var id = 1L;
        var avaliacaoNova = AvaliacaoHelper.gerarAvaliacao();
        avaliacaoNova.setNota(0);
        avaliacaoNova.setComentario("Péssimo");

        var avaliacaoAtualizada = atualizarAvaliacaoUseCase.atualizarAvaliacao(id, avaliacaoNova);

        assertThat(avaliacaoAtualizada)
                .isNotNull()
                .isInstanceOf(Avaliacao.class);
        assertThat(avaliacaoAtualizada.getAvaliacaoId()).isEqualTo(id);
        assertThat(avaliacaoAtualizada.getDataAvaliacao()).isBefore(LocalDateTime.now());
        assertThat(avaliacaoAtualizada.getUsuario().getUsuarioId()).isEqualTo(avaliacaoNova.getUsuario().getUsuarioId());
        assertThat(avaliacaoAtualizada.getRestaurante().getRestauranteId()).isEqualTo(avaliacaoNova.getRestaurante().getRestauranteId());
        assertThat(avaliacaoAtualizada.getNota()).isEqualTo(avaliacaoNova.getNota());
        assertThat(avaliacaoAtualizada.getComentario()).isEqualTo(avaliacaoNova.getComentario());
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarAvaliacao_IdNaoEncontrado() {
        var id = 123456798L;
        var avaliacaoNova = AvaliacaoHelper.gerarAvaliacao();

        assertThatThrownBy(() -> atualizarAvaliacaoUseCase.atualizarAvaliacao(id, avaliacaoNova))
                .isInstanceOf(AvaliacaoNotFoundException.class)
                .hasMessage("Avaliação de id: " + id + " não encontrada.");
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarAvaliacao_NotaInvalida() {
        var id = 1L;
        var avaliacaoNova = AvaliacaoHelper.gerarAvaliacao();
        avaliacaoNova.setNota(3000);

        assertThatThrownBy(() -> atualizarAvaliacaoUseCase.atualizarAvaliacao(id, avaliacaoNova))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("A Nota deve ser entre 0 e 5");
    }

}
