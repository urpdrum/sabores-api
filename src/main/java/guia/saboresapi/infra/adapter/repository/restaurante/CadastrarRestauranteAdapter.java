package guia.saboresapi.infra.adapter.repository.restaurante;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.CadastrarRestauranteInterface;
import guia.saboresapi.infra.entity.RestauranteEntity;
import guia.saboresapi.infra.repository.RestauranteRepository;
import guia.saboresapi.infra.repository.mapper.RestauranteEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class CadastrarRestauranteAdapter implements CadastrarRestauranteInterface {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteEntityMapper entityMapper;


    @Override
    @Transactional
    public Restaurante cadastrarRestaurante(Restaurante restaurante) {

        RestauranteEntity restauranteEntity = entityMapper.toRestauranteEntity(restaurante);

        restauranteEntity = restauranteRepository.save(restauranteEntity);

        return entityMapper.toRestaurante(restauranteEntity);
    }
}
