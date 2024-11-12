package guia.saboresapi.domain.gateway.restaurante;



import guia.saboresapi.domain.entity.Restaurante;

import java.util.List;

public interface BuscarRestaurantePorTipoCozinhaInterface {
  List<Restaurante> buscarPorTipoCozinha(String tipoCozinha);
}
