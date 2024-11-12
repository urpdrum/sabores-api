package guia.saboresapi.domain.usecase.avaliacao.integracao;


import guia.saboresapi.domain.exception.avaliacao.AvaliacaoNotFoundException;
import guia.saboresapi.domain.usecase.avaliacao.DeletarAvaliacaoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class DeletarAvaliacaoUseCaseIT {

    @Autowired
    private DeletarAvaliacaoUseCase deletarAvaliacaoUseCase;

    @Test
    void devePermitirDeletarAvaliacao() {
        var id = 1L;

        var avaliacaoRemovida = deletarAvaliacaoUseCase.deletarAvaliacao(id);

        assertThat(avaliacaoRemovida).isTrue();
    }

    @Test
    void deveGerarExcecao_QuandoDeletarAvaliacao_IdNaoEncontrado() {
        var id = 123465L;

        assertThatThrownBy(() -> deletarAvaliacaoUseCase.deletarAvaliacao(id))
                .isInstanceOf(AvaliacaoNotFoundException.class)
                .hasMessage("Avaliação de id: " + id + " não encontrada.");
    }
}
