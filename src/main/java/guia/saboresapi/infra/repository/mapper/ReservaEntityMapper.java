package guia.saboresapi.infra.repository.mapper;


import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.infra.entity.ReservaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservaEntityMapper {

    ReservaEntityMapper INSTANCE = Mappers.getMapper(ReservaEntityMapper.class);

    @Mapping(target = "usuario.usuarioId", source = "usuarioEntity.usuarioId")
    @Mapping(target = "mesa.mesaId", source = "mesaEntity.mesaId")
    Reserva toReserva(ReservaEntity reservaEntity);

    @Mapping(target = "usuarioEntity.usuarioId", source = "usuario.usuarioId")
    @Mapping(target = "mesaEntity.mesaId", source = "mesa.mesaId")
    ReservaEntity toReservaEntity(Reserva reserva);
}
