package guia.saboresapi.domain.usecase.avaliacao;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorUsuarioInterface;

import java.util.List;

public class BuscarAvaliacoesPorUsuarioUseCase {

    private final BuscarAvaliacoesPorUsuarioInterface buscarAvaliacoesPorUsuarioInterface;

    public BuscarAvaliacoesPorUsuarioUseCase(BuscarAvaliacoesPorUsuarioInterface buscarAvaliacoesPorUsuarioInterface) {
        this.buscarAvaliacoesPorUsuarioInterface = buscarAvaliacoesPorUsuarioInterface;
    }

    public List<Avaliacao> buscarAvaliacoesPorUsuario(Long usuarioId) {
        return buscarAvaliacoesPorUsuarioInterface.buscarAvaliacoesPorUsuario(usuarioId);
    }
}
