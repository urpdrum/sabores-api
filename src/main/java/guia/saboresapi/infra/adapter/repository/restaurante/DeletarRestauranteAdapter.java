package guia.saboresapi.infra.adapter.repository.restaurante;


import guia.saboresapi.domain.gateway.restaurante.DeletarRestauranteInterface;
import guia.saboresapi.infra.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

@Component
public class DeletarRestauranteAdapter implements DeletarRestauranteInterface {
  private final RestauranteRepository restauranteRepository;

  public DeletarRestauranteAdapter(RestauranteRepository restauranteRepository) {
    this.restauranteRepository = restauranteRepository;
  }

  @Override
  public boolean deletarRestaurante(Long id) {
    restauranteRepository.deleteById(id);
    return true;
  }
}
