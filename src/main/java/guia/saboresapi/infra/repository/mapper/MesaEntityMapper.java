package guia.saboresapi.infra.repository.mapper;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.infra.entity.MesaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MesaEntityMapper {

    MesaEntityMapper INSTANCE = Mappers.getMapper(MesaEntityMapper.class);

    /**
     * @param mesa
     * @return
     */
    @Mapping(target = "restauranteEntity.restauranteId", source = "restaurante.restauranteId")
    @Mapping(target = "restauranteEntity.mesas", ignore = true)
    MesaEntity toMesaEntity (Mesa mesa);

    /**
     * @param mesaEntity
     * @return
     */
    @Mapping(target = "restaurante.restauranteId", source = "restauranteEntity.restauranteId")
    @Mapping(target = "restaurante.mesas", ignore = true)
    Mesa toMesa(MesaEntity mesaEntity);


}
