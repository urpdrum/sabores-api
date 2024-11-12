package guia.saboresapi.infra.adapter.repository.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorTipoCozinhaInterface;
import guia.saboresapi.infra.repository.RestauranteRepository;
import guia.saboresapi.infra.repository.mapper.RestauranteEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarRestaurantePorTipoCozinhaAdapter implements BuscarRestaurantePorTipoCozinhaInterface {
  private final RestauranteRepository restauranteRepository;
  private final RestauranteEntityMapper restauranteMapper;

  @Override
  public List<Restaurante> buscarPorTipoCozinha(String tipoCozinha) {
    return restauranteRepository.findByTipoDeCozinha(tipoCozinha).stream().map(restauranteMapper::toRestaurante).toList();
  }
}
