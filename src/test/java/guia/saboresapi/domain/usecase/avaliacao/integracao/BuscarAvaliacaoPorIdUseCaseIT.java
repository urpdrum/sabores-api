package guia.saboresapi.domain.usecase.avaliacao.integracao;

import com.fiap.tc.restaurantes.domain.entity.Avaliacao;
import com.fiap.tc.restaurantes.domain.exception.avaliacao.AvaliacaoNotFoundException;
import com.fiap.tc.restaurantes.domain.usecase.avaliacao.BuscarAvaliacaoPorIdUseCase;
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
class BuscarAvaliacaoPorIdUseCaseIT {

    @Autowired
    private BuscarAvaliacaoPorIdUseCase buscarAvaliacaoPorIdUseCase;

    @Test
    void devePermitirBuscarAvaliacaoPorId() {
        var id = 1L;

        var avaliacaoObtida = buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(id);

        assertThat(avaliacaoObtida)
                .isNotNull()
                .isInstanceOf(Avaliacao.class);
        assertThat(avaliacaoObtida.getAvaliacaoId()).isPositive();
        assertThat(avaliacaoObtida.getDataAvaliacao()).isBefore(LocalDateTime.now());
        assertThat(avaliacaoObtida.getNota()).isBetween(0,5);
        assertThat(avaliacaoObtida.getComentario()).isNotNull();
        assertThat(avaliacaoObtida.getRestaurante()).isNotNull();
        assertThat(avaliacaoObtida.getUsuario()).isNotNull();
    }

    @Test
    void deveGerarExcecao_QuandoBuscarAvaliacaoPorId_IdNaoEncontrado() {
        var id = 123465789L;

        assertThatThrownBy(() -> buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(id))
                .isInstanceOf(AvaliacaoNotFoundException.class)
                .hasMessage("Avaliação de id: " + id + " não encontrada.");
    }
}
