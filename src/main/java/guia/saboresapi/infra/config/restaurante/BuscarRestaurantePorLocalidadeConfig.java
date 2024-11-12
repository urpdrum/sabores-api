package guia.saboresapi.infra.config.restaurante;


import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorLocalidadeInterface;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorLocalidadeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarRestaurantePorLocalidadeConfig {

  @Bean
  public BuscarRestaurantePorLocalidadeUseCase buscarRestaurantesPorLocalidadeUseCase(
          BuscarRestaurantePorLocalidadeInterface buscarRestaurantePorLocalidadeInterface
  ) {
    return new BuscarRestaurantePorLocalidadeUseCase(buscarRestaurantePorLocalidadeInterface);
  }
}
