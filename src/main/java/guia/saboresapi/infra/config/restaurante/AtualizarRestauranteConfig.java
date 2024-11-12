package guia.saboresapi.infra.config.restaurante;



import guia.saboresapi.domain.gateway.restaurante.AtualizarRestauranteInterface;
import guia.saboresapi.domain.gateway.restaurante.ConsultarEnderecoPorCepInterface;
import guia.saboresapi.domain.usecase.restaurante.AtualizarRestauranteUseCase;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarRestauranteConfig {

  /**
   * @param atualizarRestauranteInterface
   * @param consultarEnderecoPorCepInterface
   * @return
   */
  @Bean
  public AtualizarRestauranteUseCase atualizarRestauranteUseCase(AtualizarRestauranteInterface atualizarRestauranteInterface,
                                                                 ConsultarEnderecoPorCepInterface consultarEnderecoPorCepInterface,
                                                                 BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase) {
    return new AtualizarRestauranteUseCase(atualizarRestauranteInterface, consultarEnderecoPorCepInterface, buscarRestaurantePorIdUseCase);
  }
}
