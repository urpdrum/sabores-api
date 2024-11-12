package guia.saboresapi.infra.repository.mapper;


import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.infra.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {
  UsuarioEntityMapper INSTANCE = Mappers.getMapper(UsuarioEntityMapper.class);

  /**
   * @param usuario
   * @return
   */
  UsuarioEntity toUsuarioEntity(Usuario usuario);

  /**
   * @param usuarioEntity
   * @return
   */
  Usuario toUsuario(UsuarioEntity usuarioEntity);
}
