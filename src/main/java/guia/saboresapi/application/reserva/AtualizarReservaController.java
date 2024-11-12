package guia.saboresapi.application.reserva;

import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.input.reserva.AtualizarReservaRequest;
import guia.saboresapi.domain.mapper.reserva.ReservaMapper;
import guia.saboresapi.domain.output.reserva.ReservaResponse;
import guia.saboresapi.domain.usecase.reserva.AtualizarReservaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class AtualizarReservaController {

    private final AtualizarReservaUseCase atualizarReservaUseCase;
    private final ReservaMapper mapper;

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponse> atualizarReserva(@PathVariable Long id, @RequestBody AtualizarReservaRequest request) {
        Reserva reserva = atualizarReservaUseCase.atualizarReserva(id, mapper.toReserva(request));
        return ResponseEntity.ok(mapper.toReservaResponse(reserva));
    }
}
