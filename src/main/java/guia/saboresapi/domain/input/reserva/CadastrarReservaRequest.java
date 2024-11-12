package guia.saboresapi.domain.input.reserva;

import java.time.LocalDateTime;

public record CadastrarReservaRequest(
        Long usuarioId,
        Long mesaId,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {
}
