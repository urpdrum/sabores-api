package guia.saboresapi.infra.config.usuario;

import guia.saboresapi.domain.gateway.usuario.AtualizarUsuarioInterface;
import guia.saboresapi.domain.usecase.usuario.AtualizarUsuarioUseCase;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarUsuarioConfig {
  @Bean
  public AtualizarUsuarioUseCase atualizarUsuarioUseCase(
      AtualizarUsuarioInterface atualizarUsuarioInterface,
      BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase
  ) {
    return new AtualizarUsuarioUseCase(atualizarUsuarioInterface, buscarUsuarioPorIdUseCase);
  }
}
