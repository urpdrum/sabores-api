package guia.saboresapi.domain.usecase.usuario;


import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.gateway.usuario.AtualizarUsuarioInterface;

public class AtualizarUsuarioUseCase {
  private final AtualizarUsuarioInterface atualizarUsuarioInterface;
  private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

  public AtualizarUsuarioUseCase(AtualizarUsuarioInterface atualizarUsuarioInterface, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
    this.atualizarUsuarioInterface = atualizarUsuarioInterface;
    this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
  }

  public Usuario atualizarUsuario(Long id, Usuario usuario) {
    Usuario usuarioBuscado = buscarUsuarioPorIdUseCase.buscarUsuarioPorId(id);

    usuarioBuscado.setNome(usuario.getNome());
    usuarioBuscado.setTelefone(usuario.getTelefone());
    usuarioBuscado.setSenha(usuario.getSenha());

    return atualizarUsuarioInterface.atualizarUsuario(id, usuarioBuscado);
  }
}
