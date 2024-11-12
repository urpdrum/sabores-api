package guia.saboresapi.infra.adapter.repository.avaliacao;

import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.gateway.avaliacao.AtualizarAvaliacaoInterface;
import guia.saboresapi.infra.entity.AvaliacaoEntity;
import guia.saboresapi.infra.repository.AvaliacaoRepository;
import guia.saboresapi.infra.repository.mapper.AvaliacaoEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AtualizarAvaliacaoAdapter implements AtualizarAvaliacaoInterface {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoEntityMapper mapper;

    @Override
    @Transactional
    public Avaliacao atualizarAvaliacao(Avaliacao avaliacao) {
        AvaliacaoEntity avaliacaoEntity = mapper.toAvaliacaoEntity(avaliacao);

        return mapper.toAvaliacao(avaliacaoRepository.save(avaliacaoEntity));
    }
}
