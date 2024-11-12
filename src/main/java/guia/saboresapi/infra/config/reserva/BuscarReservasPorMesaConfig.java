package guia.saboresapi.infra.config.reserva;


import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorMesaInterface;
import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorMesaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarReservasPorMesaConfig {
    @Bean
    BuscarReservasPorMesaUseCase buscarReservasPorMesaUseCase(BuscarReservasPorMesaInterface buscarReservasPorMesaInterface) {
        return new BuscarReservasPorMesaUseCase(buscarReservasPorMesaInterface);
    }
}
