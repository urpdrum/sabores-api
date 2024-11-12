package guia.saboresapi.infra.config.usuario;
import guia.saboresapi.domain.gateway.usuario.CadastrarUsuarioInterface;
import guia.saboresapi.domain.usecase.usuario.CadastrarUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastrarUsuarioConfig {

  @Bean
  public CadastrarUsuarioUseCase cadastrarUsuarioUseCase(CadastrarUsuarioInterface cadastrarUsuarioInterface) {
    return new CadastrarUsuarioUseCase(cadastrarUsuarioInterface);
  }
}
