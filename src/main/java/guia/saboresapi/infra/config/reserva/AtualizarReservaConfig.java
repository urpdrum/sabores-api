package guia.saboresapi.infra.config.reserva;


import guia.saboresapi.domain.gateway.reserva.AtualizarReservaInterface;
import guia.saboresapi.domain.usecase.reserva.AtualizarReservaUseCase;
import guia.saboresapi.domain.usecase.reserva.BuscarReservaPorIdUseCase;
import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorMesaEPeriodoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarReservaConfig {
    @Bean
    AtualizarReservaUseCase atualizarReservaUseCase(BuscarReservaPorIdUseCase buscarReservaPorIdUseCase,
                                                    AtualizarReservaInterface atualizarReservaInterface,
                                                    BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase) {
        return new AtualizarReservaUseCase(buscarReservaPorIdUseCase, atualizarReservaInterface, buscarReservasPorMesaEPeriodoUseCase);
    }
}
