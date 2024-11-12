package guia.saboresapi.infra.adapter.repository.avaliacao;

import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.gateway.avaliacao.CadastrarAvaliacaoInterface;
import guia.saboresapi.infra.entity.AvaliacaoEntity;
import guia.saboresapi.infra.repository.AvaliacaoRepository;
import guia.saboresapi.infra.repository.mapper.AvaliacaoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CadastrarAvaliacaoAdapter implements CadastrarAvaliacaoInterface {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoEntityMapper mapper;

    @Override
    public Avaliacao cadastraAvaliacao(Avaliacao avaliacao) {
        AvaliacaoEntity avaliacaoEntity = avaliacaoRepository.save(mapper.toAvaliacaoEntity(avaliacao));
        return mapper.toAvaliacao(avaliacaoEntity);
    }
}
