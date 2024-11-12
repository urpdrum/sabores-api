package guia.saboresapi.domain.usecase.avaliacao;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.exception.avaliacao.AvaliacaoNotFoundException;
import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacaoPorIdInterface;

public class BuscarAvaliacaoPorIdUseCase {

    private final BuscarAvaliacaoPorIdInterface buscarAvaliacaoPorIdInterface;

    public BuscarAvaliacaoPorIdUseCase(BuscarAvaliacaoPorIdInterface buscarAvaliacaoPorIdInterface) {
        this.buscarAvaliacaoPorIdInterface = buscarAvaliacaoPorIdInterface;
    }

    public Avaliacao buscarAvaliacaoPorId(Long id) {

        Avaliacao avaliacao = buscarAvaliacaoPorIdInterface.buscarAvaliacaoPorId(id);
        if (avaliacao == null) {
            throw new AvaliacaoNotFoundException("Avaliação de id: " + id + " não encontrada.");
        }

        return avaliacao;
    }
}
