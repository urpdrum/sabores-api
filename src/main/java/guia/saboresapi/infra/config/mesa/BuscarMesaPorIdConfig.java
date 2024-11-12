package guia.saboresapi.infra.config.mesa;


import guia.saboresapi.domain.gateway.mesa.BuscarMesaPorIdInterface;
import guia.saboresapi.domain.usecase.mesa.BuscarMesaPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarMesaPorIdConfig {
    @Bean
    public BuscarMesaPorIdUseCase buscarMesaPorIdUseCase(BuscarMesaPorIdInterface buscarMesaPorIdInterface) {
        return new BuscarMesaPorIdUseCase(buscarMesaPorIdInterface);
    }
}