package guia.saboresapi.infra.config.reserva;


import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorMesaEPeriodoInterface;
import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorMesaEPeriodoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarReservasPorMesaEPeriodoConfig {
    @Bean
    BuscarReservasPorMesaEPeriodoUseCase buscarReservasPorMesaEPeriodoUseCase(BuscarReservasPorMesaEPeriodoInterface buscarReservasPorMesaEPeriodoInterface) {
        return new BuscarReservasPorMesaEPeriodoUseCase(buscarReservasPorMesaEPeriodoInterface);
    }
}
