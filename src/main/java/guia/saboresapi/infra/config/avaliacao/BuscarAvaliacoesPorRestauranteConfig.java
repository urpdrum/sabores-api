package guia.saboresapi.infra.config.avaliacao;

import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorRestauranteInterface;
import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacoesPorRestauranteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarAvaliacoesPorRestauranteConfig {

    @Bean
    BuscarAvaliacoesPorRestauranteUseCase buscarAvaliacoesPorRestauranteUseCase(BuscarAvaliacoesPorRestauranteInterface buscarAvaliacoesPorRestauranteInterface) {
        return new BuscarAvaliacoesPorRestauranteUseCase(buscarAvaliacoesPorRestauranteInterface);
    }
}
