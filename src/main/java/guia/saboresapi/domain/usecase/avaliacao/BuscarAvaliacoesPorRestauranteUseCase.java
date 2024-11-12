package guia.saboresapi.domain.usecase.avaliacao;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorRestauranteInterface;

import java.util.List;

public class BuscarAvaliacoesPorRestauranteUseCase {

    private final BuscarAvaliacoesPorRestauranteInterface buscarAvaliacoesPorRestaurante;

    public BuscarAvaliacoesPorRestauranteUseCase(BuscarAvaliacoesPorRestauranteInterface buscarAvaliacoesPorRestaurante) {
        this.buscarAvaliacoesPorRestaurante = buscarAvaliacoesPorRestaurante;
    }

    public List<Avaliacao> buscarAvaliacoesPorRestaurante(Long restauranteId) {
        return buscarAvaliacoesPorRestaurante.buscarAvaliacoesPorRestaurante(restauranteId);
    }
}
