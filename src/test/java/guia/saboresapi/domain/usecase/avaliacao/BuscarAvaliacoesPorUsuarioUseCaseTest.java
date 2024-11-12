package guia.saboresapi.domain.usecase.avaliacao;


import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorUsuarioInterface;
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

class BuscarAvaliacoesPorUsuarioUseCaseTest {

    private BuscarAvaliacoesPorUsuarioUseCase buscarAvaliacoesPorUsuarioUseCase;

    @Mock
    private BuscarAvaliacoesPorUsuarioInterface buscarAvaliacoesPorUsuarioInterface;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        buscarAvaliacoesPorUsuarioUseCase = new BuscarAvaliacoesPorUsuarioUseCase(buscarAvaliacoesPorUsuarioInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirBuscarAvaliacoesPorUsuario() {
        var avaliacao1 = AvaliacaoHelper.gerarAvaliacao();
        avaliacao1.setAvaliacaoId(1L);
        var avaliacao2 = AvaliacaoHelper.gerarAvaliacao();
        avaliacao2.setAvaliacaoId(2L);
        var listAvaliacoes = Arrays.asList(avaliacao1, avaliacao2);
        when(buscarAvaliacoesPorUsuarioInterface.buscarAvaliacoesPorUsuario(anyLong())).thenReturn(listAvaliacoes);

        var listaObtida = buscarAvaliacoesPorUsuarioUseCase.buscarAvaliacoesPorUsuario(1L);

        assertThat(listaObtida)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(avaliacao1, avaliacao2);
    }
}
