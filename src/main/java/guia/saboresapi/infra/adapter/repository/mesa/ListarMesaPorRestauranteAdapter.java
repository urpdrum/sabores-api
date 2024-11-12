package guia.saboresapi.infra.adapter.repository.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.gateway.mesa.ListarMesasPorRestauranteInterface;
import guia.saboresapi.infra.repository.MesaRepository;
import guia.saboresapi.infra.repository.mapper.MesaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarMesaPorRestauranteAdapter implements ListarMesasPorRestauranteInterface {

    private final MesaRepository mesaRepository;
    private final MesaEntityMapper mesaEntityMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Mesa> listarMesasPorRestaurante(Long restauranteId) {
        return mesaRepository.findByRestaurante(restauranteId)
                .stream()
                .map(mesaEntityMapper::toMesa)
                .toList();
    }
}
