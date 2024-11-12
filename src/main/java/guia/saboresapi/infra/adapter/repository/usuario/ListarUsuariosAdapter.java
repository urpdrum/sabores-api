package guia.saboresapi.infra.adapter.repository.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.gateway.usuario.ListarUsuariosInterface;
import guia.saboresapi.infra.repository.UsuarioRepository;
import guia.saboresapi.infra.repository.mapper.UsuarioEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarUsuariosAdapter implements ListarUsuariosInterface {
  private final UsuarioRepository usuarioRepository;
  private final UsuarioEntityMapper usuarioEntityMapper;

  @Override
  public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll().stream().map(usuarioEntityMapper::toUsuario).toList();
  }
}
