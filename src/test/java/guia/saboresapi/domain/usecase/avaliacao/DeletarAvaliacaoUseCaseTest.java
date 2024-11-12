package guia.saboresapi.domain.usecase.avaliacao;


import guia.saboresapi.domain.exception.avaliacao.AvaliacaoNotFoundException;
import guia.saboresapi.domain.gateway.avaliacao.DeletarAvaliacaoInterface;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DeletarAvaliacaoUseCaseTest {

    private DeletarAvaliacaoUseCase deletarAvaliacaoUseCase;

    @Mock
    private DeletarAvaliacaoInterface deletarAvaliacaoInterface;

    @Mock
    private BuscarAvaliacaoPorIdUseCase buscarAvaliacaoPorIdUseCase;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        deletarAvaliacaoUseCase = new DeletarAvaliacaoUseCase(deletarAvaliacaoInterface, buscarAvaliacaoPorIdUseCase);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirDeletarAvaliacao() {
        when(buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(anyLong())).thenReturn(AvaliacaoHelper.gerarAvaliacao());
        when(deletarAvaliacaoInterface.deletarAvaliacao(anyLong())).thenReturn(true);

        var avaliacaRemovida = deletarAvaliacaoUseCase.deletarAvaliacao(1L);

        assertThat(avaliacaRemovida).isTrue();
        verify(buscarAvaliacaoPorIdUseCase, times(1)).buscarAvaliacaoPorId(anyLong());
        verify(deletarAvaliacaoInterface, times(1)).deletarAvaliacao(anyLong());
    }

    @Test
    void deveGerarExcecao_QuandoDeletarAvaliacao_IdNaoEncontrado() {
        when(buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(anyLong())).thenThrow(new AvaliacaoNotFoundException("Avaliacao não encontrada"));

        assertThatThrownBy(() -> deletarAvaliacaoUseCase.deletarAvaliacao(1L))
                .isInstanceOf(AvaliacaoNotFoundException.class)
                .hasMessage("Avaliacao não encontrada");
        verify(buscarAvaliacaoPorIdUseCase, times(1)).buscarAvaliacaoPorId(anyLong());
        verify(deletarAvaliacaoInterface, never()).deletarAvaliacao(anyLong());
    }

    @Test
    void naoDeveDeletarAvaliacao_QuandoDeletarAvaliacao_ExceptionDeRepository() {
        when(buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(anyLong())).thenReturn(AvaliacaoHelper.gerarAvaliacao());
        when(deletarAvaliacaoInterface.deletarAvaliacao(anyLong())).thenThrow(new RuntimeException("Generic Database Exception"));

        var avaliacaRemovida = deletarAvaliacaoUseCase.deletarAvaliacao(1L);

        assertThat(avaliacaRemovida).isFalse();
        verify(buscarAvaliacaoPorIdUseCase, times(1)).buscarAvaliacaoPorId(anyLong());
        verify(deletarAvaliacaoInterface, times(1)).deletarAvaliacao(anyLong());
    }
}
