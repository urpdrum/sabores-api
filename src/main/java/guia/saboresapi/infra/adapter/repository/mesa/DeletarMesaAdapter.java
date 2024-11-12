package guia.saboresapi.infra.adapter.repository.mesa;


import guia.saboresapi.domain.gateway.mesa.DeletarMesaInterface;
import guia.saboresapi.infra.repository.MesaRepository;
import org.springframework.stereotype.Component;

@Component
public class DeletarMesaAdapter implements DeletarMesaInterface {

    private final MesaRepository mesaRepository;

    public DeletarMesaAdapter(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public boolean deletarMesa(Long mesaId) {
        mesaRepository.deleteById(mesaId);
        return true;
    }

}
