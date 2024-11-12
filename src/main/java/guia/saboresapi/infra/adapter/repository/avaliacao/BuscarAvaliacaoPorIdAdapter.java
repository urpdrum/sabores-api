package guia.saboresapi.infra.adapter.repository.avaliacao;

import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacaoPorIdInterface;
import guia.saboresapi.infra.repository.AvaliacaoRepository;
import guia.saboresapi.infra.repository.mapper.AvaliacaoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BuscarAvaliacaoPorIdAdapter implements BuscarAvaliacaoPorIdInterface {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoEntityMapper mapper;

    @Override
    public Avaliacao buscarAvaliacaoPorId(Long id) {
        return mapper.toAvaliacao(avaliacaoRepository.findById(id).orElse(null));
    }
}
