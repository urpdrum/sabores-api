package guia.saboresapi.infra.config.restaurante;

import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorRestauranteInterface;
import guia.saboresapi.domain.gateway.avaliacao.DeletarAvaliacaoInterface;
import guia.saboresapi.domain.gateway.restaurante.DeletarRestauranteInterface;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorIdUseCase;
import guia.saboresapi.domain.usecase.restaurante.DeletarRestauranteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletarRestauranteConfig {

  @Bean
  public DeletarRestauranteUseCase deletarRestauranteUseCase(
          DeletarRestauranteInterface outputPort,
          DeletarAvaliacaoInterface deletarAvaliacaoInterface,
          BuscarAvaliacoesPorRestauranteInterface buscarAvaliacoesPorRestaurante,
          BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase
  ) {
    return new DeletarRestauranteUseCase(outputPort, buscarAvaliacoesPorRestaurante, deletarAvaliacaoInterface, buscarRestaurantePorIdUseCase);
  }
}
