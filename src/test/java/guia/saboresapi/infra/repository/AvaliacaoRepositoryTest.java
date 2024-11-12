package guia.saboresapi.infra.repository;


import guia.saboresapi.infra.entity.AvaliacaoEntity;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AvaliacaoRepositoryTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class CadastrarAvaliacao {

        @Test
        void devePermitirCadastrarAvaliacao() {
            var avaliacaoEntity = AvaliacaoHelper.gerarAvaliacaoEntity();
            when(avaliacaoRepository.save(any(AvaliacaoEntity.class))).thenReturn(avaliacaoEntity);

            var avaliacaoArmazenada = avaliacaoRepository.save(avaliacaoEntity);

            assertThat(avaliacaoArmazenada)
                    .isInstanceOf(AvaliacaoEntity.class)
                    .isEqualTo(avaliacaoEntity);
            verify(avaliacaoRepository, times(1)).save(any(AvaliacaoEntity.class));
        }

    }

    @Nested
    class BuscarAvaliacao {

        @Test
        void devePermitirBuscarAvaliacaoPorId() {
            var avaliacaoEntity = AvaliacaoHelper.gerarAvaliacaoEntity();
            when(avaliacaoRepository.findById(any(Long.class))).thenReturn(Optional.of(avaliacaoEntity));

            var avaliacaoObtidaOpt = avaliacaoRepository.findById(1L);

            assertThat(avaliacaoObtidaOpt)
                    .isPresent()
                    .contains(avaliacaoEntity);
            avaliacaoObtidaOpt.ifPresent(avaliacaoObtida -> {
                assertThat(avaliacaoObtida.getNota()).isEqualTo(avaliacaoEntity.getNota());
                assertThat(avaliacaoObtida.getComentario()).isEqualTo(avaliacaoEntity.getComentario());
                assertThat(avaliacaoObtida.getRestauranteEntity()).isEqualTo(avaliacaoEntity.getRestauranteEntity());
                assertThat(avaliacaoObtida.getUsuarioEntity()).isEqualTo(avaliacaoEntity.getUsuarioEntity());
                assertThat(avaliacaoObtida.getDataAvaliacao()).isEqualTo(avaliacaoEntity.getDataAvaliacao());
            });
            verify(avaliacaoRepository, times(1)).findById(anyLong());
        }

        @Test
        void devePermitirBuscarAvaliacaoPorRestaurante() {
            var avaliacaoEntity = AvaliacaoHelper.gerarAvaliacaoEntity();
            var avaliacaoEntity2 = AvaliacaoHelper.gerarAvaliacaoEntity();
            when(avaliacaoRepository.buscarPorRestaurante(anyLong())).thenReturn(Arrays.asList(avaliacaoEntity, avaliacaoEntity2));

            var avaliacaoEntityList = avaliacaoRepository.buscarPorRestaurante(1L);

            assertThat(avaliacaoEntityList)
                    .isNotEmpty()
                    .hasSize(2)
                    .containsExactlyInAnyOrder(avaliacaoEntity, avaliacaoEntity2);

            verify(avaliacaoRepository, times(1)).buscarPorRestaurante(anyLong());
        }

        @Test
        void devePermitirBuscarAvaliacaoPorUsuario() {
            var avaliacaoEntity = AvaliacaoHelper.gerarAvaliacaoEntity();
            var avaliacaoEntity2 = AvaliacaoHelper.gerarAvaliacaoEntity();
            when(avaliacaoRepository.buscarPorUsuario(anyLong())).thenReturn(Arrays.asList(avaliacaoEntity, avaliacaoEntity2));

            var avaliacaoEntityList = avaliacaoRepository.buscarPorUsuario(1L);

            assertThat(avaliacaoEntityList)
                    .isNotEmpty()
                    .hasSize(2)
                    .containsExactlyInAnyOrder(avaliacaoEntity, avaliacaoEntity2);

            verify(avaliacaoRepository, times(1)).buscarPorUsuario(anyLong());
        }

    }

    @Nested
    class DeletarAvaliacao {

        @Test
        void devePermitirDeletarAvaliacao() {
            doNothing().when(avaliacaoRepository).deleteById(anyLong());
            avaliacaoRepository.deleteById(1L);
            verify(avaliacaoRepository, times(1)).deleteById(anyLong());
        }

    }

}
