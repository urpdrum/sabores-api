package guia.saboresapi.infra.config.avaliacao;

import guia.saboresapi.domain.gateway.avaliacao.AtualizarAvaliacaoInterface;
import guia.saboresapi.domain.usecase.avaliacao.AtualizarAvaliacaoUseCase;
import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacaoPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtualizarAvaliacaoConfig {

    @Bean
    AtualizarAvaliacaoUseCase atualizarAvaliacaoUseCase(AtualizarAvaliacaoInterface atualizarAvaliacaoInterface,
                                                        BuscarAvaliacaoPorIdUseCase buscarAvaliacaoPorIdUseCase) {

        return new AtualizarAvaliacaoUseCase(atualizarAvaliacaoInterface, buscarAvaliacaoPorIdUseCase);
    }
}
