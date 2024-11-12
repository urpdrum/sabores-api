package guia.saboresapi.infra.config.mesa;

import guia.saboresapi.domain.gateway.mesa.AtualizarMesaInterface;
import guia.saboresapi.domain.usecase.mesa.AtualizarMesaUseCase;
import guia.saboresapi.domain.usecase.mesa.BuscarMesaPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarMesaConfig {
    @Bean
    public AtualizarMesaUseCase atualizarMesaUseCase(AtualizarMesaInterface atualizarMesaInterface, BuscarMesaPorIdUseCase buscarMesaPorIdUseCase) {
        return new AtualizarMesaUseCase(atualizarMesaInterface, buscarMesaPorIdUseCase);
    }
}