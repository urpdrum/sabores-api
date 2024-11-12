package guia.saboresapi.infra.config.usuario;

import guia.saboresapi.domain.gateway.usuario.BuscarUsuarioPorIdInterface;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarUsuarioPorIdConfig {
  @Bean
  public BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase(BuscarUsuarioPorIdInterface buscarUsuarioPorIdInterface) {
    return new BuscarUsuarioPorIdUseCase(buscarUsuarioPorIdInterface);
  }
}
