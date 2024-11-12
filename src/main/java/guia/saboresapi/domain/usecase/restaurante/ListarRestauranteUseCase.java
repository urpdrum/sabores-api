package guia.saboresapi.domain.usecase.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.ListarRestauranteInterface;

import java.util.List;

public class ListarRestauranteUseCase {
  private final ListarRestauranteInterface listarRestauranteInterface;

  public ListarRestauranteUseCase(ListarRestauranteInterface listarRestauranteInterface) {
    this.listarRestauranteInterface = listarRestauranteInterface;
  }

  public List<Restaurante> listarRestaurantes() {
    return listarRestauranteInterface.listarRestaurantes();
  }
}
