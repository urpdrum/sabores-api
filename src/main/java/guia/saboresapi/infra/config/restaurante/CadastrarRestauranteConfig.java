package guia.saboresapi.infra.config.restaurante;

import guia.saboresapi.domain.gateway.restaurante.CadastrarRestauranteInterface;
import guia.saboresapi.domain.gateway.restaurante.ConsultarEnderecoPorCepInterface;
import guia.saboresapi.domain.usecase.restaurante.CadastrarRestauranteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastrarRestauranteConfig {

    /**
     * @param cadastrarRestauranteInterface
     * @param consultarEnderecoPorCepInterface
     * @return
     */
    @Bean
    public CadastrarRestauranteUseCase cadastrarRestauranteUseCase(CadastrarRestauranteInterface cadastrarRestauranteInterface,
                                                                   ConsultarEnderecoPorCepInterface consultarEnderecoPorCepInterface) {
        return new CadastrarRestauranteUseCase(cadastrarRestauranteInterface, consultarEnderecoPorCepInterface);
    }
}
