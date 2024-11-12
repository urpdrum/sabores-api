package guia.saboresapi.domain.usecase.avaliacao;



import guia.saboresapi.domain.gateway.avaliacao.AtualizarAvaliacaoInterface;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AtualizarAvaliacaoUseCaseTest {

    private AtualizarAvaliacaoUseCase atualizarAvaliacaoUseCase;

    @Mock
    private AtualizarAvaliacaoInterface atualizarAvaliacaoInterface;

    @Mock
    private BuscarAvaliacaoPorIdUseCase buscarAvaliacaoPorIdUseCase;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        atualizarAvaliacaoUseCase = new AtualizarAvaliacaoUseCase(atualizarAvaliacaoInterface, buscarAvaliacaoPorIdUseCase);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirAtualizarAvaliacao() {
        var avaliacaoAntiga = AvaliacaoHelper.gerarAvaliacao();
        avaliacaoAntiga.setAvaliacaoId(1L);
        var avaliacaoNova = AvaliacaoHelper.gerarAvaliacao();
        avaliacaoNova.setNota(2);
        avaliacaoNova.setComentario("comentário novo");
        when(buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(anyLong())).thenReturn(avaliacaoAntiga);
        when(atualizarAvaliacaoInterface.atualizarAvaliacao(any(Avaliacao.class))).thenAnswer(answer -> answer.getArgument(0));

        var avaliacaoAtualizada = atualizarAvaliacaoUseCase.atualizarAvaliacao(1L, avaliacaoNova);

        assertThat(avaliacaoAtualizada)
                .isInstanceOf(Avaliacao.class)
                .isNotNull();
        assertThat(avaliacaoAtualizada.getAvaliacaoId()).isEqualTo(avaliacaoAntiga.getAvaliacaoId());
        assertThat(avaliacaoAtualizada.getDataAvaliacao()).isEqualTo(avaliacaoAntiga.getDataAvaliacao());
        assertThat(avaliacaoAtualizada.getUsuario()).isEqualTo(avaliacaoAntiga.getUsuario());
        assertThat(avaliacaoAtualizada.getRestaurante()).isEqualTo(avaliacaoAntiga.getRestaurante());

        assertThat(avaliacaoAtualizada.getNota()).isEqualTo(avaliacaoNova.getNota());
        assertThat(avaliacaoAtualizada.getComentario()).isEqualTo(avaliacaoNova.getComentario());
        verify(buscarAvaliacaoPorIdUseCase, times(1)).buscarAvaliacaoPorId(anyLong());
        verify(atualizarAvaliacaoInterface, times(1)).atualizarAvaliacao(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarAvaliacao_IdNaoEncontrado() {
        var avaliacao = AvaliacaoHelper.gerarAvaliacao();
        when(buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(anyLong())).thenThrow(new AvaliacaoNotFoundException("Avaliacao não encontrada"));

        assertThatThrownBy(() -> atualizarAvaliacaoUseCase.atualizarAvaliacao(1L, avaliacao))
                .isInstanceOf(AvaliacaoNotFoundException.class)
                .hasMessage("Avaliacao não encontrada");
        verify(buscarAvaliacaoPorIdUseCase, times(1)).buscarAvaliacaoPorId(anyLong());
        verify(atualizarAvaliacaoInterface, never()).atualizarAvaliacao(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarAvaliacao_NotaInvalida() {
        var avaliacaoAntiga = AvaliacaoHelper.gerarAvaliacao();
        avaliacaoAntiga.setAvaliacaoId(1L);
        var avaliacaoNova = AvaliacaoHelper.gerarAvaliacao();
        avaliacaoNova.setNota(10000);
        when(buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(anyLong())).thenReturn(avaliacaoAntiga);
        when(atualizarAvaliacaoInterface.atualizarAvaliacao(any(Avaliacao.class))).thenAnswer(answer -> answer.getArgument(0));

        assertThatThrownBy(() -> atualizarAvaliacaoUseCase.atualizarAvaliacao(1L, avaliacaoNova))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("A Nota deve ser entre 0 e 5");
        verify(buscarAvaliacaoPorIdUseCase, times(1)).buscarAvaliacaoPorId(anyLong());
        verify(atualizarAvaliacaoInterface, never()).atualizarAvaliacao(any(Avaliacao.class));
    }

}
