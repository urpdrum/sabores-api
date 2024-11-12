package guia.saboresapi.domain.exception.restaurante;

public class RestauranteNotFoundException extends RuntimeException {
    public RestauranteNotFoundException(String message) {
        super(message);
    }
}
