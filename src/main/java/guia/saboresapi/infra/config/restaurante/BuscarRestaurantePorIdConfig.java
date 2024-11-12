package guia.saboresapi.infra.config.restaurante;


import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorIdInterface;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarRestaurantePorIdConfig {

    @Bean
    BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase(BuscarRestaurantePorIdInterface buscarRestaurantePorIdInterface) {
        return new BuscarRestaurantePorIdUseCase(buscarRestaurantePorIdInterface);
    }
}
