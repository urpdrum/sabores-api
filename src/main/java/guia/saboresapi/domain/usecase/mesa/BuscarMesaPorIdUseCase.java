package guia.saboresapi.domain.usecase.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.exception.mesa.MesaNotFoundException;
import guia.saboresapi.domain.gateway.mesa.BuscarMesaPorIdInterface;

public class BuscarMesaPorIdUseCase {

    private final BuscarMesaPorIdInterface buscarMesaPorIdInterface;

    public BuscarMesaPorIdUseCase(BuscarMesaPorIdInterface buscarMesaPorIdInterface) {
        this.buscarMesaPorIdInterface = buscarMesaPorIdInterface;
    }

    public Mesa buscarMesaPorId(Long mesaId) {
        Mesa mesa = buscarMesaPorIdInterface.buscarMesaPorId(mesaId);

        if(mesa == null) {
            throw new MesaNotFoundException("Mesa de id: " + mesaId + " não encontrada");
        }

        return mesa;
    }
}