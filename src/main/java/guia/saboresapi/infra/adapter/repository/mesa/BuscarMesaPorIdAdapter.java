package guia.saboresapi.infra.adapter.repository.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.gateway.mesa.BuscarMesaPorIdInterface;
import guia.saboresapi.infra.entity.MesaEntity;
import guia.saboresapi.infra.repository.MesaRepository;
import guia.saboresapi.infra.repository.mapper.MesaEntityMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BuscarMesaPorIdAdapter implements BuscarMesaPorIdInterface {

    private final MesaRepository mesaRepository;
    private final MesaEntityMapper mesaEntityMapper;
       public BuscarMesaPorIdAdapter(MesaRepository mesaRepository, MesaEntityMapper mesaEntityMapper) {
        this.mesaRepository = mesaRepository;
        this.mesaEntityMapper = mesaEntityMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Mesa buscarMesaPorId(Long id) {
        MesaEntity mesaBuscada = mesaRepository.findById(id)
                .orElse(null);
        return mesaEntityMapper.toMesa(mesaBuscada);
    }
}