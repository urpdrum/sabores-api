package guia.saboresapi.infra.config.mesa;


import guia.saboresapi.domain.gateway.mesa.DeletarMesaInterface;
import guia.saboresapi.domain.usecase.mesa.BuscarMesaPorIdUseCase;
import guia.saboresapi.domain.usecase.mesa.DeletarMesaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletarMesaConfig {
    @Bean
    public DeletarMesaUseCase deletarMesaUseCase(DeletarMesaInterface deletarMesaInterface, BuscarMesaPorIdUseCase buscarMesaPorIdUseCase) {
        return new DeletarMesaUseCase(deletarMesaInterface, buscarMesaPorIdUseCase);
    }
}