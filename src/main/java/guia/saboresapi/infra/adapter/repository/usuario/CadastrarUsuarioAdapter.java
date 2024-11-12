package guia.saboresapi.infra.adapter.repository.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.gateway.usuario.CadastrarUsuarioInterface;
import guia.saboresapi.infra.entity.UsuarioEntity;
import guia.saboresapi.infra.repository.UsuarioRepository;
import guia.saboresapi.infra.repository.mapper.UsuarioEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarUsuarioAdapter implements CadastrarUsuarioInterface {
  private final UsuarioRepository usuarioRepository;
  private final UsuarioEntityMapper usuarioEntityMapper;


  @Override
  public Usuario cadastrarUsuario(Usuario usuario) {
    UsuarioEntity usuarioEntity = usuarioEntityMapper.toUsuarioEntity(usuario);
    usuarioRepository.save(usuarioEntity);
    return usuarioEntityMapper.toUsuario(usuarioEntity);
  }
}
