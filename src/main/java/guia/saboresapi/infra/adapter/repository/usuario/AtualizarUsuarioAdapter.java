package guia.saboresapi.infra.adapter.repository.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.gateway.usuario.AtualizarUsuarioInterface;
import guia.saboresapi.infra.entity.UsuarioEntity;
import guia.saboresapi.infra.repository.UsuarioRepository;
import guia.saboresapi.infra.repository.mapper.UsuarioEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarUsuarioAdapter implements AtualizarUsuarioInterface {
  private final UsuarioRepository usuarioRepository;
  private final UsuarioEntityMapper usuarioEntityMapper;
  private final BuscarUsuarioPorIdAdapter buscarUsuarioPorIdAdapter;

  @Override
  public Usuario atualizarUsuario(Long id, Usuario usuario) {
    UsuarioEntity usuarioAtualizado = usuarioRepository.save(usuarioEntityMapper.toUsuarioEntity(usuario));

    return usuarioEntityMapper.toUsuario(usuarioAtualizado);
  }
}
