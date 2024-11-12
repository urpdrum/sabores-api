package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorUsuarioInterface;
import guia.saboresapi.utils.reserva.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class BuscarReservasPorUsuarioUseCaseTest {

    private BuscarReservasPorUsuarioUseCase buscarReservasPorUsuarioUseCase;

    @Mock
    private BuscarReservasPorUsuarioInterface buscarReservasPorUsuarioInterface;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        buscarReservasPorUsuarioUseCase = new BuscarReservasPorUsuarioUseCase(buscarReservasPorUsuarioInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirBuscarReservasPorUsuario() {
        var reserva1 = ReservaHelper.gerarReserva();
        var reserva2 = ReservaHelper.gerarReserva();
        var list = Arrays.asList(reserva1, reserva2);
        when(buscarReservasPorUsuarioInterface.buscarReservasPorUsuario(anyLong()))
                .thenReturn(list);

        var listObtida = buscarReservasPorUsuarioUseCase.buscarReservasPorUsuario(1L);

        assertThat(listObtida)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(reserva1, reserva2);
    }
}
