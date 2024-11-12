package guia.saboresapi.infra.config.restaurante;

import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorTipoCozinhaInterface;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorTipoCozinhaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarRestaurantePorTipoCozinhaConfig {

  @Bean
  public BuscarRestaurantePorTipoCozinhaUseCase buscarRestaurantePorTipoCozinhaUseCase(
      BuscarRestaurantePorTipoCozinhaInterface buscarRestaurantePorTipoCozinhaInterface
  ) {
    return new BuscarRestaurantePorTipoCozinhaUseCase(buscarRestaurantePorTipoCozinhaInterface);
  }
}
