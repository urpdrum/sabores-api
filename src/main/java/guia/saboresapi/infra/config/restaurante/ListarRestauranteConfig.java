package guia.saboresapi.infra.config.restaurante;

import guia.saboresapi.domain.usecase.restaurante.ListarRestauranteUseCase;
import guia.saboresapi.domain.gateway.restaurante.ListarRestauranteInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListarRestauranteConfig {

  /**
   * @param outputPort
   * @return
   */
  @Bean
  public ListarRestauranteUseCase listarRestauranteUseCase(ListarRestauranteInterface outputPort) {
    return new ListarRestauranteUseCase(outputPort);
  }
}
