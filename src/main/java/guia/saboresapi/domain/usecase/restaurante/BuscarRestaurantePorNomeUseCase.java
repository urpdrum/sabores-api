package guia.saboresapi.domain.usecase.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorNomeInterface;

import java.util.List;

public class BuscarRestaurantePorNomeUseCase {
  private final BuscarRestaurantePorNomeInterface buscarRestaurantePorNomeInterface;

  public BuscarRestaurantePorNomeUseCase(BuscarRestaurantePorNomeInterface buscarRestaurantePorNomeInterface) {
    this.buscarRestaurantePorNomeInterface = buscarRestaurantePorNomeInterface;
  }

  public List<Restaurante> buscarRestaurantePorNome(String nome) {
    return buscarRestaurantePorNomeInterface.buscarRestaurantePorNome(nome);
  }
}
