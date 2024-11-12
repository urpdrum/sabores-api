package guia.saboresapi.infra.config.avaliacao;

import guia.saboresapi.domain.gateway.avaliacao.CadastrarAvaliacaoInterface;
import guia.saboresapi.domain.usecase.avaliacao.CadastrarAvaliacaoUseCase;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorIdUseCase;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastrarAvaliacaoConfig {

    @Bean
    CadastrarAvaliacaoUseCase cadastrarAvaliacaoUseCase(CadastrarAvaliacaoInterface cadastrarAvaliacaoInterface,
                                                        BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
                                                        BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase) {
        return new CadastrarAvaliacaoUseCase(cadastrarAvaliacaoInterface, buscarUsuarioPorIdUseCase, buscarRestaurantePorIdUseCase);
    }
}
