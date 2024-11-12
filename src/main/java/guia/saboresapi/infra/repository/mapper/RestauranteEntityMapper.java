package guia.saboresapi.infra.repository.mapper;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.infra.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RestauranteEntityMapper {

    RestauranteEntityMapper INSTANCE = Mappers.getMapper(RestauranteEntityMapper.class);

    /**
     * @param restaurante
     * @return
     */
    @Mapping(target = "mesas", ignore = true)
    RestauranteEntity toRestauranteEntity(Restaurante restaurante);

    /**
     * @param restauranteEntity
     * @return
     */
    @Mapping(target = "mesas", ignore = true)
    Restaurante toRestaurante(RestauranteEntity restauranteEntity);
}
