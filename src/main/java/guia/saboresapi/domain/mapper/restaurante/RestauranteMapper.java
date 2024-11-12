package guia.saboresapi.domain.mapper.restaurante;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.input.restaurante.AtualizarRestauranteRequest;
import guia.saboresapi.domain.input.restaurante.CadastrarRestauranteRequest;
import guia.saboresapi.domain.output.restaurante.RestauranteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    RestauranteMapper INSTANCE = Mappers.getMapper(RestauranteMapper.class);

    /**
     * @param restauranteRequest
     * @return
     */
    @Mapping(target = "restauranteId", ignore = true)
    Restaurante toRestaurante(CadastrarRestauranteRequest restauranteRequest);

    @Mapping(target = "restauranteId", ignore = true)
    Restaurante toRestaurante(AtualizarRestauranteRequest restauranteRequest);

    /**
     * @param restaurante
     * @return
     */
    RestauranteResponse toRestauranteResponse(Restaurante restaurante);

}
