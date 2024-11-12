package guia.saboresapi.domain.usecase.reserva.integracao;


import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorMesaEPeriodoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class BuscarReservasPorMesaEPeriodoUseCaseIT {

    @Autowired
    private BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase;

    @Test
    void devePermitirBuscarReservasPorMesaEPeriodo() {
        var mesaId = 1L;
        var dataInicio = LocalDateTime.of(2030,9,10,11,47,37);
        var dataFim = LocalDateTime.of(2030,9,10,12,47,37);

        var reservaListObtida = buscarReservasPorMesaEPeriodoUseCase.buscarReservasPorMesaEPeriodo(mesaId, dataInicio, dataFim);

        assertThat(reservaListObtida)
                .isNotEmpty()
                .hasSize(1);
    }
}
