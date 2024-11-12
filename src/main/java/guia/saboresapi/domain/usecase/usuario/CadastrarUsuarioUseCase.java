package guia.saboresapi.domain.usecase.usuario;


import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.gateway.usuario.CadastrarUsuarioInterface;

public class CadastrarUsuarioUseCase {
  private final CadastrarUsuarioInterface cadastrarUsuarioInterface;

  public CadastrarUsuarioUseCase(CadastrarUsuarioInterface cadastrarUsuarioInterface) {
    this.cadastrarUsuarioInterface = cadastrarUsuarioInterface;
  }

  public Usuario cadastrarUsuario(Usuario usuario) {
    return cadastrarUsuarioInterface.cadastrarUsuario(usuario);
  }
}
