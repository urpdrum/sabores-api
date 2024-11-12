package guia.saboresapi.infra.adapter.repository.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.gateway.usuario.BuscarUsuarioPorIdInterface;
import guia.saboresapi.infra.entity.UsuarioEntity;
import guia.saboresapi.infra.repository.UsuarioRepository;
import guia.saboresapi.infra.repository.mapper.UsuarioEntityMapper;
import org.springframework.stereotype.Component;


@Component
public class BuscarUsuarioPorIdAdapter implements BuscarUsuarioPorIdInterface {
  private final UsuarioRepository usuarioRepository;
  private final UsuarioEntityMapper usuarioEntityMapper;

  public BuscarUsuarioPorIdAdapter(UsuarioRepository usuarioRepository, UsuarioEntityMapper usuarioEntityMapper) {
    this.usuarioRepository = usuarioRepository;
    this.usuarioEntityMapper = usuarioEntityMapper;
  }

  @Override
  public Usuario buscarUsuarioPorId(Long id) {
    UsuarioEntity usuarioBuscado = usuarioRepository
        .findById(id)
        .orElse(null);

    return usuarioEntityMapper.toUsuario(usuarioBuscado);
  }
}
