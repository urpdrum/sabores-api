package guia.saboresapi.infra.config.restaurante;


import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorNomeInterface;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorNomeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarRestaurantePorNomeConfig {

  @Bean
  public BuscarRestaurantePorNomeUseCase buscarRestaurantesPorNomeUseCase(
      BuscarRestaurantePorNomeInterface buscarRestaurantePorNomeInterface
  ) {
    return new BuscarRestaurantePorNomeUseCase(buscarRestaurantePorNomeInterface);
  }
}
