package guia.saboresapi.infra.config.avaliacao;

import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacaoPorIdInterface;
import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacaoPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarAvaliacaoPorIdConfig {

    @Bean
    BuscarAvaliacaoPorIdUseCase buscarAvaliacaoPorIdUseCase(BuscarAvaliacaoPorIdInterface buscarAvaliacaoPorIdInterface) {
        return new BuscarAvaliacaoPorIdUseCase(buscarAvaliacaoPorIdInterface);
    }
}
