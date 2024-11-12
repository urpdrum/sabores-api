package guia.saboresapi.infra.adapter.repository.avaliacao;

import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.gateway.avaliacao.BuscarAvaliacoesPorUsuarioInterface;
import guia.saboresapi.infra.entity.AvaliacaoEntity;
import guia.saboresapi.infra.repository.AvaliacaoRepository;
import guia.saboresapi.infra.repository.mapper.AvaliacaoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BuscarAvaliacoesPorUsuarioAdapter implements BuscarAvaliacoesPorUsuarioInterface {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoEntityMapper mapper;

    @Override
    public List<Avaliacao> buscarAvaliacoesPorUsuario(Long usuarioId) {
        List<AvaliacaoEntity> avaliacaoEntities = avaliacaoRepository.buscarPorUsuario(usuarioId);
        return avaliacaoEntities.stream().map(mapper::toAvaliacao).toList();
    }
}
