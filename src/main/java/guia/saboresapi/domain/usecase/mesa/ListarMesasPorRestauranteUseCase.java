package guia.saboresapi.domain.usecase.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.gateway.mesa.ListarMesasPorRestauranteInterface;

import java.util.List;

public class ListarMesasPorRestauranteUseCase {

    private final ListarMesasPorRestauranteInterface listarMesasPorRestauranteInterface;

    public ListarMesasPorRestauranteUseCase(ListarMesasPorRestauranteInterface listarMesasPorRestauranteInterface) {
        this.listarMesasPorRestauranteInterface = listarMesasPorRestauranteInterface;
    }

    public List<Mesa> listarMesasPorRestaurante(Long restauranteId) {
        return listarMesasPorRestauranteInterface.listarMesasPorRestaurante(restauranteId);
    }
}