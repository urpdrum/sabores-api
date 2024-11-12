package guia.saboresapi.domain.gateway.restaurante;



import guia.saboresapi.domain.entity.Restaurante;

import java.util.List;

public interface BuscarRestaurantePorLocalidadeInterface {
  List<Restaurante> buscarRestaurantePorLocalidade(String localidade);
}
