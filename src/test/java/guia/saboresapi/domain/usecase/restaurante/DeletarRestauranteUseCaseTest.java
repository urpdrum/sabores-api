package guia.saboresapi.domain.usecase.restaurante;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorRestauranteInterface;
import guia.saboresapi.domain.gateway.avaliacao.DeletarAvaliacaoInterface;
import guia.saboresapi.domain.gateway.restaurante.DeletarRestauranteInterface;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeletarRestauranteUseCaseTest {
    private DeletarRestauranteUseCase useCase;

    @Mock
    private DeletarRestauranteInterface deletarRestauranteInterface;

    @Mock
    private DeletarAvaliacaoInterface deletarAvaliacaoInterface;

    @Mock
    private BuscarAvaliacoesPorRestauranteInterface buscarAvaliacoesPorRestauranteInterface;

    @Mock
    private BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

    AutoCloseable openMocks;


    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new DeletarRestauranteUseCase(
                deletarRestauranteInterface,
                buscarAvaliacoesPorRestauranteInterface,
                deletarAvaliacaoInterface,
                buscarRestaurantePorIdUseCase
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirDeletarRestaurante_ComAvaliacao() {
        // Arrange
        Avaliacao avaliacao1 = AvaliacaoHelper.gerarAvaliacao();
        Avaliacao avaliacao2 = AvaliacaoHelper.gerarAvaliacao();
        Avaliacao avaliacao3 = AvaliacaoHelper.gerarAvaliacao();
        avaliacao1.setAvaliacaoId(1L);
        avaliacao2.setAvaliacaoId(2L);
        avaliacao3.setAvaliacaoId(3L);
        List<Avaliacao> avaliacoes = List.of(avaliacao1, avaliacao2, avaliacao3);
        var identificador = 1L;

        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(RestauranteHelper.gerarRestauranteValido());
        when(buscarAvaliacoesPorRestauranteInterface.buscarAvaliacoesPorRestaurante(any(Long.class))).thenReturn(avaliacoes);
        when(deletarAvaliacaoInterface.deletarAvaliacao(any(Long.class))).thenReturn(true);
        when(deletarRestauranteInterface.deletarRestaurante(any(Long.class))).thenReturn(true);


        // Act
        useCase.deletarRestaurante(identificador);

        // Assert
        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(deletarRestauranteInterface, times(1)).deletarRestaurante(any(Long.class));
        verify(buscarAvaliacoesPorRestauranteInterface, times(1)).buscarAvaliacoesPorRestaurante(any(Long.class));
        verify(deletarAvaliacaoInterface, times(3)).deletarAvaliacao(any(Long.class));
    }

    @Test
    void devePermitirDeletarRestaurante_SemAvaliacao() {
        // Arrange
        List<Avaliacao> avaliacoes = List.of();
        var identificador = 3L;

        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenReturn(RestauranteHelper.gerarRestauranteValido());
        when(buscarAvaliacoesPorRestauranteInterface.buscarAvaliacoesPorRestaurante(any(Long.class))).thenReturn(avaliacoes);
        when(deletarRestauranteInterface.deletarRestaurante(any(Long.class))).thenReturn(true);


        // Act
        useCase.deletarRestaurante(identificador);

        // Assert
        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(deletarRestauranteInterface, times(1)).deletarRestaurante(any(Long.class));
        verify(buscarAvaliacoesPorRestauranteInterface, times(1)).buscarAvaliacoesPorRestaurante(any(Long.class));
        verify(deletarAvaliacaoInterface, never()).deletarAvaliacao(any(Long.class));
    }

    @Test
    void deveGerarExcecao_QuandoDeletarRestaurante_IdNaoEncontrado() {
        var id = 123456L;
        var mensagemException = "Restaurante de id: " + id + " não encontrado.";
        when(buscarRestaurantePorIdUseCase.buscarRestaurantePorId(anyLong())).thenThrow(new RestauranteNotFoundException(mensagemException));

        assertThatThrownBy(() -> useCase.deletarRestaurante(id))
                .isInstanceOf(RestauranteNotFoundException.class)
                .hasMessage(mensagemException);

        verify(buscarRestaurantePorIdUseCase, times(1)).buscarRestaurantePorId(anyLong());
        verify(deletarRestauranteInterface, never()).deletarRestaurante(any(Long.class));
        verify(buscarAvaliacoesPorRestauranteInterface, never()).buscarAvaliacoesPorRestaurante(any(Long.class));
        verify(deletarAvaliacaoInterface, never()).deletarAvaliacao(any(Long.class));
    }
}