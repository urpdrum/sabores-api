package guia.saboresapi.infra.adapter.repository.restaurante;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.AtualizarRestauranteInterface;
import guia.saboresapi.infra.entity.RestauranteEntity;
import guia.saboresapi.infra.repository.RestauranteRepository;
import guia.saboresapi.infra.repository.mapper.RestauranteEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AtualizarRestauranteAdapter implements AtualizarRestauranteInterface {

  private final RestauranteRepository restauranteRepository;
  private final RestauranteEntityMapper entityMapper;

  @Override
  @Transactional
  public Restaurante atualizarRestaurante(Restaurante restaurante) {

    RestauranteEntity restauranteAtualizado = restauranteRepository.save(entityMapper.toRestauranteEntity(restaurante));

    return entityMapper.toRestaurante(restauranteAtualizado);
  }
}
