package guia.saboresapi.domain.usecase.usuario;


import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.exception.usuario.UsuarioNotFoundException;
import guia.saboresapi.domain.gateway.usuario.BuscarUsuarioPorIdInterface;

public class BuscarUsuarioPorIdUseCase {
  private final BuscarUsuarioPorIdInterface buscarUsuarioPorIdInterface;

  public BuscarUsuarioPorIdUseCase(BuscarUsuarioPorIdInterface buscarUsuarioPorIdInterface) {
    this.buscarUsuarioPorIdInterface = buscarUsuarioPorIdInterface;
  }

  public Usuario buscarUsuarioPorId(Long id) {
    Usuario usuario = buscarUsuarioPorIdInterface.buscarUsuarioPorId(id);
    if (usuario == null) {
      throw new UsuarioNotFoundException("Usuário de id: " + id + " não encontrado.");
    }
    return usuario;
  }
}
