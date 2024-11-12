package guia.saboresapi.domain.usecase.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorLocalidadeInterface;

import java.util.List;

public class BuscarRestaurantePorLocalidadeUseCase {
  private final BuscarRestaurantePorLocalidadeInterface buscarRestaurantePorLocalidadeInterface;

  public BuscarRestaurantePorLocalidadeUseCase(BuscarRestaurantePorLocalidadeInterface buscarRestaurantePorLocalidadeInterface) {
    this.buscarRestaurantePorLocalidadeInterface = buscarRestaurantePorLocalidadeInterface;
  }

  public List<Restaurante> buscarRestaurantePorLocalidade(String localidade) {
    return buscarRestaurantePorLocalidadeInterface.buscarRestaurantePorLocalidade(localidade);
  }
}
