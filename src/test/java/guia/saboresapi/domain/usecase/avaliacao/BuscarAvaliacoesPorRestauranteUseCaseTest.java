package guia.saboresapi.domain.usecase.avaliacao;


import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorRestauranteInterface;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class BuscarAvaliacoesPorRestauranteUseCaseTest {

    private BuscarAvaliacoesPorRestauranteUseCase buscarAvaliacoesPorRestauranteUseCase;

    @Mock
    private BuscarAvaliacoesPorRestauranteInterface buscarAvaliacoesPorRestauranteInterface;

    AutoCloseable mock;

    @BeforeEach
    public void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        buscarAvaliacoesPorRestauranteUseCase = new BuscarAvaliacoesPorRestauranteUseCase(buscarAvaliacoesPorRestauranteInterface);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirBuscarAvaliacoesPorRestaurante() {
        var avaliacao1 = AvaliacaoHelper.gerarAvaliacao();
        avaliacao1.setAvaliacaoId(1L);
        var avaliacao2 = AvaliacaoHelper.gerarAvaliacao();
        avaliacao2.setAvaliacaoId(2L);
        var listAvaliacoes = Arrays.asList(avaliacao1, avaliacao2);
        when(buscarAvaliacoesPorRestauranteInterface.buscarAvaliacoesPorRestaurante(anyLong())).thenReturn(listAvaliacoes);

        var listaObtida = buscarAvaliacoesPorRestauranteUseCase.buscarAvaliacoesPorRestaurante(1L);

        assertThat(listaObtida)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(avaliacao1, avaliacao2);
    }
}
