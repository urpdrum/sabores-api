package guia.saboresapi.infra.config.usuario;


import guia.saboresapi.domain.gateway.usuario.DeletarUsuarioInterface;
import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacoesPorUsuarioUseCase;
import guia.saboresapi.domain.usecase.avaliacao.DeletarAvaliacaoUseCase;
import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorUsuarioUseCase;
import guia.saboresapi.domain.usecase.reserva.DeletarReservaUseCase;
import guia.saboresapi.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;
import guia.saboresapi.domain.usecase.usuario.DeletarUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeletarUsuarioConfig {
  @Bean
  public DeletarUsuarioUseCase deletarUsuarioUseCase(
      DeletarUsuarioInterface deletarUsuarioInterface,
      BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
      BuscarAvaliacoesPorUsuarioUseCase buscarAvaliacoesPorUsuarioUseCase,
      DeletarAvaliacaoUseCase deletarAvaliacaoUseCase,
      BuscarReservasPorUsuarioUseCase buscarReservasPorUsuarioUseCase,
      DeletarReservaUseCase deletarReservaUseCase
  ) {
    return new DeletarUsuarioUseCase(
        deletarUsuarioInterface,
        buscarUsuarioPorIdUseCase,
        buscarAvaliacoesPorUsuarioUseCase,
        deletarAvaliacaoUseCase,
        buscarReservasPorUsuarioUseCase,
        deletarReservaUseCase
    );
  }
}
