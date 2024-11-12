package guia.saboresapi.domain.usecase.avaliacao;


import guia.saboresapi.domain.gateway.avaliacao.DeletarAvaliacaoInterface;

public class DeletarAvaliacaoUseCase {

    private final DeletarAvaliacaoInterface deletarAvaliacaoInterface;
    private final BuscarAvaliacaoPorIdUseCase buscarAvaliacaoPorIdUseCase;

    public DeletarAvaliacaoUseCase(DeletarAvaliacaoInterface deletarAvaliacaoInterface, BuscarAvaliacaoPorIdUseCase buscarAvaliacaoPorIdUseCase) {
        this.deletarAvaliacaoInterface = deletarAvaliacaoInterface;
        this.buscarAvaliacaoPorIdUseCase = buscarAvaliacaoPorIdUseCase;
    }

    public boolean deletarAvaliacao(Long avaliacaoId) {

        //Valida se avaliacao existe
        buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(avaliacaoId);

        try {
            deletarAvaliacaoInterface.deletarAvaliacao(avaliacaoId);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
