package guia.saboresapi.infra.config.avaliacao;

import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorUsuarioInterface;
import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacoesPorUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscarAvaliacoesPorUsuarioConfig {

    @Bean
    BuscarAvaliacoesPorUsuarioUseCase buscarAvaliacoesPorUsuarioUseCase(BuscarAvaliacoesPorUsuarioInterface buscarAvaliacoesPorUsuarioInterface) {
        return new BuscarAvaliacoesPorUsuarioUseCase(buscarAvaliacoesPorUsuarioInterface);
    }
}
