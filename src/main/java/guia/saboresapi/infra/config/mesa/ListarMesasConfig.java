package guia.saboresapi.infra.config.mesa;


import guia.saboresapi.domain.gateway.mesa.ListarMesasPorRestauranteInterface;
import guia.saboresapi.domain.usecase.mesa.ListarMesasPorRestauranteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListarMesasConfig {
    @Bean
    public ListarMesasPorRestauranteUseCase listarMesasUseCase(ListarMesasPorRestauranteInterface listarMesasPorRestauranteInterface) {
        return new ListarMesasPorRestauranteUseCase(listarMesasPorRestauranteInterface);
    }
}