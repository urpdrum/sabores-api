package guia.saboresapi.application.reserva;

import guia.saboresapi.domain.entity.Reserva;
import guia.saboresapi.domain.input.reserva.CadastrarReservaRequest;
import guia.saboresapi.domain.mapper.reserva.ReservaMapper;
import guia.saboresapi.domain.output.reserva.ReservaResponse;
import guia.saboresapi.domain.usecase.reserva.CadastrarReservaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class CadastrarReservaController {

    private final CadastrarReservaUseCase cadastrarReservaUseCase;
    private final ReservaMapper mapper;

    @PostMapping
    public ResponseEntity<ReservaResponse> cadastrarReserva(@RequestBody CadastrarReservaRequest request) {
        Reserva reserva = cadastrarReservaUseCase.cadastrarReserva(mapper.toReserva(request), request.usuarioId(), request.mesaId());
        return ResponseEntity.ok(mapper.toReservaResponse(reserva));
    }
}
