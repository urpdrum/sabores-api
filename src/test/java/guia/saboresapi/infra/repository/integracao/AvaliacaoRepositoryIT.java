package guia.saboresapi.infra.repository.integracao;


import guia.saboresapi.infra.entity.AvaliacaoEntity;
import guia.saboresapi.infra.repository.AvaliacaoRepository;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class AvaliacaoRepositoryIT {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Test
    void devePermitirCriarTabela() {
        var totalDeRegistros = avaliacaoRepository.count();
        assertThat(totalDeRegistros).isPositive();
    }

    @Nested
    class CadastrarAvaliacao {

        @Test
        void devePermitirCadastrarAvaliacao() {
            var avaliacao = AvaliacaoHelper.gerarAvaliacaoEntity();

            var avaliacaoArmazenada = avaliacaoRepository.save(avaliacao);

            assertThat(avaliacaoArmazenada)
                    .isInstanceOf(AvaliacaoEntity.class)
                    .isNotNull();
            assertThat(avaliacaoArmazenada.getAvaliacaoId()).isNotNull().isPositive();
            assertThat(avaliacaoArmazenada.getUsuarioEntity()).isEqualTo(avaliacao.getUsuarioEntity());
            assertThat(avaliacaoArmazenada.getRestauranteEntity()).isEqualTo(avaliacao.getRestauranteEntity());
            assertThat(avaliacaoArmazenada.getNota()).isEqualTo(avaliacao.getNota());
            assertThat(avaliacaoArmazenada.getComentario()).isEqualTo(avaliacao.getComentario());
            assertThat(avaliacaoArmazenada.getDataAvaliacao()).isEqualTo(avaliacao.getDataAvaliacao());
        }

    }

    @Nested
    class BuscarAvaliacao {

        @Test
        void devePermitirBuscarAvaliacaoPorId() {
            var avaliacaoObtidaOpt = avaliacaoRepository.findById(1L);

            assertThat(avaliacaoObtidaOpt).isPresent();
            avaliacaoObtidaOpt.ifPresent(avaliacaoEntity -> {
                assertThat(avaliacaoEntity.getAvaliacaoId()).isEqualTo(1L);
            });
        }

        @Test
        void devePermitirBuscarAvaliacaoPorRestaurante() {
            var avaliacaoList = avaliacaoRepository.buscarPorRestaurante(1L);
            assertThat(avaliacaoList).isNotEmpty().hasSize(2);
        }

        @Test
        void devePermitirBuscarAvaliacaoPorUsuario() {
            var avaliacaoList = avaliacaoRepository.buscarPorUsuario(1L);
            assertThat(avaliacaoList).isNotEmpty().hasSize(2);
        }

    }

    @Nested
    class DeletarAvaliacao {

        @Test
        void devePermitirDeletarAvaliacao() {
            avaliacaoRepository.deleteById(1L);
            var avaliacaoObtidaOpt = avaliacaoRepository.findById(1L);
            assertThat(avaliacaoObtidaOpt).isNotPresent();
        }

    }

}
