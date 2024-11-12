package guia.saboresapi.infra.adapter.repository.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorIdInterface;
import guia.saboresapi.infra.entity.RestauranteEntity;
import guia.saboresapi.infra.repository.RestauranteRepository;
import guia.saboresapi.infra.repository.mapper.RestauranteEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BuscarRestaurantePorIdAdapter implements BuscarRestaurantePorIdInterface {

    private final RestauranteRepository restauranteRepository;
    private final RestauranteEntityMapper mapper;

    @Override
    public Restaurante buscarRestaurantePorId(Long id) {
        RestauranteEntity restauranteEntity = restauranteRepository.findById(id).orElse(null);
        return mapper.toRestaurante(restauranteEntity);
    }
}
