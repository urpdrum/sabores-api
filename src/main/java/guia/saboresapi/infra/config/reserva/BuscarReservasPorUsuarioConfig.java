package guia.saboresapi.infra.config.reserva;





import guia.saboresapi.domain.gateway.reserva.BuscarReservasPorUsuarioInterface;
import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarReservasPorUsuarioConfig {
    @Bean
    BuscarReservasPorUsuarioUseCase buscarReservasPorUsuarioUseCase(BuscarReservasPorUsuarioInterface buscarReservasPorUsuarioInterface) {
        return new BuscarReservasPorUsuarioUseCase(buscarReservasPorUsuarioInterface);
    }
}
