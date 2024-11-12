package guia.saboresapi.infra.adapter.repository.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorNomeInterface;
import guia.saboresapi.infra.repository.RestauranteRepository;
import guia.saboresapi.infra.repository.mapper.RestauranteEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarRestaurantePorNomeAdapter implements BuscarRestaurantePorNomeInterface {
  private final RestauranteRepository restauranteRepository;
  private final RestauranteEntityMapper restauranteMapper;

  @Override
  public List<Restaurante> buscarRestaurantePorNome(String nome) {
    return restauranteRepository.findByNomeContaining(nome).stream().map(restauranteMapper::toRestaurante).toList();
  }
}
