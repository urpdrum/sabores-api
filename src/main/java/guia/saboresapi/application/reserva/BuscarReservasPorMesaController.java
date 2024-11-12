package guia.saboresapi.application.reserva;

import guia.saboresapi.domain.mapper.reserva.ReservaMapper;
import guia.saboresapi.domain.output.reserva.ReservaResponse;
import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorMesaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class BuscarReservasPorMesaController {

    private final BuscarReservasPorMesaUseCase buscarReservasPorMesaUseCase;
    private final ReservaMapper mapper;

    @GetMapping("/mesa")
    public ResponseEntity<List<ReservaResponse>> buscarReservasPorMesa(@RequestParam Long mesaId) {
        List<ReservaResponse> responseList = buscarReservasPorMesaUseCase.buscarReservasPorMesa(mesaId)
                .stream()
                .map(mapper::toReservaResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }
}
