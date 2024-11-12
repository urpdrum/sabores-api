package guia.saboresapi.infra.repository.mapper;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.infra.entity.AvaliacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AvaliacaoEntityMapper {

    AvaliacaoEntityMapper INSTANCE = Mappers.getMapper(AvaliacaoEntityMapper.class);

    /**
     * @param avaliacaoEntity
     * @return
     */
    @Mapping(target = "restaurante.restauranteId", source = "restauranteEntity.restauranteId")
    @Mapping(target = "usuario.usuarioId", source = "usuarioEntity.usuarioId")
    @Mapping(target = "restaurante.mesas", ignore = true)
    Avaliacao toAvaliacao(AvaliacaoEntity avaliacaoEntity);

    /**
     * @param avaliacao
     * @return
     */
    @Mapping(target = "restauranteEntity.restauranteId", source = "restaurante.restauranteId")
    @Mapping(target = "usuarioEntity.usuarioId", source = "usuario.usuarioId")
    AvaliacaoEntity toAvaliacaoEntity(Avaliacao avaliacao);
}
