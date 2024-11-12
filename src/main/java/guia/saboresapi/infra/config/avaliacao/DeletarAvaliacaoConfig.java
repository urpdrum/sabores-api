package guia.saboresapi.infra.config.avaliacao;

import guia.saboresapi.domain.gateway.avaliacao.DeletarAvaliacaoInterface;
import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacaoPorIdUseCase;
import guia.saboresapi.domain.usecase.avaliacao.DeletarAvaliacaoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletarAvaliacaoConfig {

    @Bean
    DeletarAvaliacaoUseCase deletarAvaliacaoUseCase(DeletarAvaliacaoInterface deletarAvaliacaoInterface,
                                                    BuscarAvaliacaoPorIdUseCase buscarAvaliacaoPorIdUseCase) {
        return new DeletarAvaliacaoUseCase(deletarAvaliacaoInterface, buscarAvaliacaoPorIdUseCase);
    }
}
