package guia.saboresapi.domain.usecase.restaurante;


import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.exception.restaurante.RestauranteNotFoundException;
import guia.saboresapi.domain.gateway.restaurante.BuscarRestaurantePorIdInterface;

public class BuscarRestaurantePorIdUseCase {

    private final BuscarRestaurantePorIdInterface buscarRestaurantePorIdInterface;

    public BuscarRestaurantePorIdUseCase(BuscarRestaurantePorIdInterface buscarRestaurantePorIdInterface) {
        this.buscarRestaurantePorIdInterface = buscarRestaurantePorIdInterface;
    }

    public Restaurante buscarRestaurantePorId(Long id) {
        Restaurante restaurante = buscarRestaurantePorIdInterface.buscarRestaurantePorId(id);
        if (restaurante == null) {
            throw new RestauranteNotFoundException("Restaurante de id: " + id + " não encontrado.");
        }
        return restaurante;
    }
}
