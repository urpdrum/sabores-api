package guia.saboresapi.domain.entity.validation.reserva;



import guia.saboresapi.domain.entity.Reserva;

import java.time.LocalDateTime;

public class ReservaValidator {

    public static void validate(final Reserva reserva) {
        if(reserva.getDataInicio().isBefore(LocalDateTime.now()) || reserva.getDataFim().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Só é possível reservar para datas futuras.");
        }

        if (reserva.getDataInicio().isAfter(reserva.getDataFim())) {
            throw new IllegalArgumentException("A Data inicio da reserva deve ser anterior a data fim.");
        }
    }

    private ReservaValidator() {
    }
}
