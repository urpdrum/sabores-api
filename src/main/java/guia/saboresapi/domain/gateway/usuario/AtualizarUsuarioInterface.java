package guia.saboresapi.domain.gateway.usuario;


import guia.saboresapi.domain.entity.Usuario;

public interface AtualizarUsuarioInterface {
  Usuario atualizarUsuario(Long id, Usuario usuario);
}
