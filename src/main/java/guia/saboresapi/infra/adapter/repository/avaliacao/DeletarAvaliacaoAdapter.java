package guia.saboresapi.infra.adapter.repository.avaliacao;

import guia.saboresapi.domain.gateway.avaliacao.DeletarAvaliacaoInterface;
import guia.saboresapi.infra.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeletarAvaliacaoAdapter implements DeletarAvaliacaoInterface {

    private final AvaliacaoRepository avaliacaoRepository;

    @Override
    public boolean deletarAvaliacao(Long avaliacaoId) {
        avaliacaoRepository.deleteById(avaliacaoId);
        return true;
    }
}
