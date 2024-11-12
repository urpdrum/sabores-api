package guia.saboresapi.infra.config.usuario;

import guia.saboresapi.domain.gateway.usuario.ListarUsuariosInterface;
import guia.saboresapi.domain.usecase.usuario.ListarUsuariosUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListarUsuariosConfig {
  @Bean
  public ListarUsuariosUseCase listarUsuariosUseCase(ListarUsuariosInterface listarUsuariosInterface) {
    return new ListarUsuariosUseCase(listarUsuariosInterface);
  }
}
