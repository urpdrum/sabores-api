package guia.saboresapi.infra.adapter.repository.avaliacao;

import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorRestauranteInterface;
import guia.saboresapi.infra.entity.AvaliacaoEntity;
import guia.saboresapi.infra.repository.AvaliacaoRepository;
import guia.saboresapi.infra.repository.mapper.AvaliacaoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BuscarAvaliacoesPorRestauranteAdapter implements BuscarAvaliacoesPorRestauranteInterface {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoEntityMapper mapper;

    @Override
    public List<Avaliacao> buscarAvaliacoesPorRestaurante(Long restauranteId) {
        List<AvaliacaoEntity> avaliacoes = avaliacaoRepository.buscarPorRestaurante(restauranteId);
        return avaliacoes.stream().map(mapper::toAvaliacao).toList();
    }
}
