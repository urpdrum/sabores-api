package guia.saboresapi.domain.usecase.restaurante;



import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorRestauranteInterface;
import guia.saboresapi.domain.gateway.avaliacao.DeletarAvaliacaoInterface;
import guia.saboresapi.domain.gateway.restaurante.DeletarRestauranteInterface;

import java.util.List;

public class DeletarRestauranteUseCase {
  private final DeletarRestauranteInterface deletarRestauranteInterface;
  private final BuscarAvaliacoesPorRestauranteInterface buscarAvaliacoesPorRestauranteInterface;
  private final DeletarAvaliacaoInterface deletarAvaliacaoInterface;
  private final BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase;

  public DeletarRestauranteUseCase(
          DeletarRestauranteInterface deletarRestauranteInterface,
          BuscarAvaliacoesPorRestauranteInterface buscarAvaliacoesPorRestauranteInterface,
          DeletarAvaliacaoInterface deletarAvaliacaoInterface,
          BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase
  ) {
    this.deletarRestauranteInterface = deletarRestauranteInterface;
    this.buscarAvaliacoesPorRestauranteInterface = buscarAvaliacoesPorRestauranteInterface;
    this.deletarAvaliacaoInterface = deletarAvaliacaoInterface;
    this.buscarRestaurantePorIdUseCase = buscarRestaurantePorIdUseCase;
  }

  public boolean deletarRestaurante(Long id) {
    buscarRestaurantePorIdUseCase.buscarRestaurantePorId(id);

    List<Avaliacao> listaAvaliacaoRestaurante = buscarAvaliacoesPorRestauranteInterface.buscarAvaliacoesPorRestaurante(id);

    listaAvaliacaoRestaurante.forEach(avaliacao ->
      deletarAvaliacaoInterface.deletarAvaliacao(avaliacao.getAvaliacaoId())
    );

    return deletarRestauranteInterface.deletarRestaurante(id);
  }
}
