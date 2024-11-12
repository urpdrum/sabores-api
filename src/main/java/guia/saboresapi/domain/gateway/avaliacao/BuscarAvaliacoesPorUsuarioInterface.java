package guia.saboresapi.domain.gateway.avaliacao;



import guia.saboresapi.domain.entity.Avaliacao;

import java.util.List;

public interface BuscarAvaliacoesPorUsuarioInterface {

    List<Avaliacao> buscarAvaliacoesPorUsuario(Long usuarioId);
}
