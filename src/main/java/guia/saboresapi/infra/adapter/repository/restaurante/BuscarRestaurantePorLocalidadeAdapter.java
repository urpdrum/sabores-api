package guia.saboresapi.infra.adapter.repository.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorLocalidadeInterface;
import guia.saboresapi.infra.repository.RestauranteRepository;
import guia.saboresapi.infra.repository.mapper.RestauranteEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarRestaurantePorLocalidadeAdapter implements BuscarRestaurantePorLocalidadeInterface {
  private final RestauranteRepository restauranteRepository;
  private final RestauranteEntityMapper restauranteMapper;
  @Override
  public List<Restaurante> buscarRestaurantePorLocalidade(String localidade) {
    return restauranteRepository.findByLocalidade(localidade).stream().map(restauranteMapper::toRestaurante).toList();
  }
}
