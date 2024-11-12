package guia.saboresapi.domain.usecase.reserva;



import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorMesaInterface;
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

class BuscarReservasPorMesaUseCaseTest {

    private BuscarReservasPorMesaUseCase buscarReservasPorMesaUseCase;

    @Mock
    private BuscarReservasPorMesaInterface buscarReservasPorMesaInterface;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        buscarReservasPorMesaUseCase = new BuscarReservasPorMesaUseCase(buscarReservasPorMesaInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirBuscarReservasPorMesa() {
        var reserva1 = ReservaHelper.gerarReserva();
        var reserva2 = ReservaHelper.gerarReserva();
        var list = Arrays.asList(reserva1, reserva2);
        when(buscarReservasPorMesaInterface.buscarReservasPorMesa(anyLong()))
                .thenReturn(list);

        var listObtida = buscarReservasPorMesaUseCase.buscarReservasPorMesa(1L);

        assertThat(listObtida)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(reserva1, reserva2);
    }
}
