package guia.saboresapi.domain.exception.reserva;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(String message) {
        super(message);
    }
}
