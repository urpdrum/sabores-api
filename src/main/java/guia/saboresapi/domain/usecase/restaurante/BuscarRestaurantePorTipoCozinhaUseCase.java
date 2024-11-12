package guia.saboresapi.domain.usecase.restaurante;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorTipoCozinhaInterface;

import java.util.List;

public class BuscarRestaurantePorTipoCozinhaUseCase {
  private final BuscarRestaurantePorTipoCozinhaInterface buscarRestaurantePorTipoCozinhaInterface;

  public BuscarRestaurantePorTipoCozinhaUseCase(BuscarRestaurantePorTipoCozinhaInterface buscarRestaurantePorTipoCozinhaInterface) {
    this.buscarRestaurantePorTipoCozinhaInterface = buscarRestaurantePorTipoCozinhaInterface;
  }

  public List<Restaurante> buscarPorTipoCozinha(String tipoCozinha) {
    return buscarRestaurantePorTipoCozinhaInterface.buscarPorTipoCozinha(tipoCozinha);
  }
}
