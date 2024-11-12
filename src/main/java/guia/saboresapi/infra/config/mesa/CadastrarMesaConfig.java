package guia.saboresapi.infra.config.mesa;


import guia.saboresapi.domain.gateway.mesa.CadastrarMesaInterface;
import guia.saboresapi.domain.usecase.mesa.CadastrarMesaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastrarMesaConfig {

  @Bean
  public CadastrarMesaUseCase cadastrarMesaUseCase(CadastrarMesaInterface cadastrarMesaInterface) {
    return new CadastrarMesaUseCase(cadastrarMesaInterface);
  }
}