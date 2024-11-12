package guia.saboresapi.domain.mapper.usuario;


import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.input.usuario.AtualizarUsuarioRequest;
import guia.saboresapi.domain.input.usuario.CadastrarUsuarioRequest;
import guia.saboresapi.domain.output.usuario.UsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {


  /**
   * @param cadastrarUsuarioRequest
   * @return
   */
  @Mapping(target = "usuarioId", ignore = true)
  Usuario toUsuario(CadastrarUsuarioRequest cadastrarUsuarioRequest);

  /**
   * @param atualizarUsuarioRequest
   * @return
   */
  @Mapping(target = "usuarioId", ignore = true)
  Usuario toUsuario(AtualizarUsuarioRequest atualizarUsuarioRequest);

  /**
   * @param usuario
   * @return
   */
  UsuarioResponse toUsuarioResponse(Usuario usuario);
}
