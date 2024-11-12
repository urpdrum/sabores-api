package guia.saboresapi.infra.adapter.repository.restaurante;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.ListarRestauranteInterface;
import guia.saboresapi.infra.entity.RestauranteEntity;
import guia.saboresapi.infra.repository.RestauranteRepository;
import guia.saboresapi.infra.repository.mapper.RestauranteEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarRestauranteAdapter implements ListarRestauranteInterface {
  private final RestauranteRepository repository;
  private final RestauranteEntityMapper entityMapper;


  public ListarRestauranteAdapter(RestauranteRepository repository, RestauranteEntityMapper entityMapper) {
    this.repository = repository;
    this.entityMapper = entityMapper;
  }

  @Override
  public List<Restaurante> listarRestaurantes() {
    List<RestauranteEntity> restaurantesEntity = repository.findAll();

    return restaurantesEntity.stream().map(entityMapper::toRestaurante).toList();
  }
}
