package guia.saboresapi.domain.output.reserva;



import guia.saboresapi.domain.enums.StatusReservaEnum;

import java.time.LocalDateTime;

public record ReservaResponse(
        Long reservaId,
        Long mesaId,
        Long usuarioId,
        StatusReservaEnum status,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {
}
