package guia.saboresapi.domain.usecase.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.gateway.usuario.ListarUsuariosInterface;

import java.util.List;

public class ListarUsuariosUseCase {
  private final ListarUsuariosInterface listarUsuariosInterface;

  public ListarUsuariosUseCase(ListarUsuariosInterface listarUsuariosInterface) {
    this.listarUsuariosInterface = listarUsuariosInterface;
  }

  public List<Usuario> listarUsuarios() {
    return listarUsuariosInterface.listarUsuarios();
  }
}
