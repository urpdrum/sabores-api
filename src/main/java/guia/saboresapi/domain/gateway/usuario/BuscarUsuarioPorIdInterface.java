package guia.saboresapi.domain.gateway.usuario;


import guia.saboresapi.domain.entity.Usuario;

public interface BuscarUsuarioPorIdInterface {
  Usuario buscarUsuarioPorId(Long id);
}
