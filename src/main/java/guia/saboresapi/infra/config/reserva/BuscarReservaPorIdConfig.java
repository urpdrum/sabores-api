package guia.saboresapi.infra.config.reserva;

import guia.saboresapi.domain.gateway.reserva.BuscarReservaPorIdInterface;
import guia.saboresapi.domain.usecase.reserva.BuscarReservaPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarReservaPorIdConfig {
    @Bean
    BuscarReservaPorIdUseCase buscarReservaPorIdUseCase(BuscarReservaPorIdInterface buscarReservaPorIdInterface) {
        return new BuscarReservaPorIdUseCase(buscarReservaPorIdInterface);
    }
}
