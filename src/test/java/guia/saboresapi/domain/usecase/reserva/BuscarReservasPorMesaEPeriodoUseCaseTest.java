package guia.saboresapi.domain.usecase.reserva;


import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorMesaEPeriodoInterface;
import guia.saboresapi.utils.reserva.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class BuscarReservasPorMesaEPeriodoUseCaseTest {

    private BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase;

    @Mock
    private BuscarReservasPorMesaEPeriodoInterface buscarReservasPorMesaEPeriodoInterface;

    AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        buscarReservasPorMesaEPeriodoUseCase = new BuscarReservasPorMesaEPeriodoUseCase(buscarReservasPorMesaEPeriodoInterface);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirBuscarReservasPorMesaEPeriodo() {
        var reserva1 = ReservaHelper.gerarReserva();
        var reserva2 = ReservaHelper.gerarReserva();
        var list = Arrays.asList(reserva1, reserva2);
        when(buscarReservasPorMesaEPeriodoInterface.buscarReservasPorMesaEPeriodo(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(list);

        var listObtida = buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(1L, LocalDateTime.now(), LocalDateTime.now());

        assertThat(listObtida)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(reserva1, reserva2);
    }
}

