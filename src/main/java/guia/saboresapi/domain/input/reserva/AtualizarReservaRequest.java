package guia.saboresapi.domain.input.reserva;



import guia.saboresapi.domain.enums.StatusReservaEnum;

import java.time.LocalDateTime;

public record AtualizarReservaRequest(
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        StatusReservaEnum status
) {
}
