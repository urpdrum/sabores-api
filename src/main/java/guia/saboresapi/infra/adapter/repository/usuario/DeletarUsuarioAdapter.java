package guia.saboresapi.infra.adapter.repository.usuario;


import guia.saboresapi.domain.gateway.usuario.DeletarUsuarioInterface;
import guia.saboresapi.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletarUsuarioAdapter implements DeletarUsuarioInterface {
  private final UsuarioRepository usuarioRepository;

  @Override
  public boolean deletarUsuario(Long id) {
    usuarioRepository.deleteById(id);
    return true;
  }
}
