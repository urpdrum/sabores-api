package guia.saboresapi.infra.config.reserva;


import guia.saboresapi.domain.gateway.reserva.DeletarReservaInterface;
import guia.saboresapi.domain.usecase.reserva.BuscarReservaPorIdUseCase;
import guia.saboresapi.domain.usecase.reserva.DeletarReservaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletarReservaConfig {
    @Bean
    DeletarReservaUseCase deletarReservaUseCase(DeletarReservaInterface deletarReservaInterface, BuscarReservaPorIdUseCase buscarReservaPorIdUseCase) {
        return new DeletarReservaUseCase(buscarReservaPorIdUseCase, deletarReservaInterface);
    }
}
