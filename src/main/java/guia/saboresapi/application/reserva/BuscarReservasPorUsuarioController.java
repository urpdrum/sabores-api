package guia.saboresapi.application.reserva;

import guia.saboresapi.domain.mapper.reserva.ReservaMapper;
import guia.saboresapi.domain.output.reserva.ReservaResponse;
import guia.saboresapi.domain.usecase.reserva.BuscarReservasPorUsuarioUseCase;
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
public class BuscarReservasPorUsuarioController {

    private final BuscarReservasPorUsuarioUseCase buscarReservasPorUsuarioUseCase;
    private final ReservaMapper mapper;

    @GetMapping("/usuario")
    public ResponseEntity<List<ReservaResponse>> buscarReservasPorMesa(@RequestParam Long usuarioId) {
        List<ReservaResponse> responseList = buscarReservasPorUsuarioUseCase.buscarReservasPorUsuario(usuarioId)
                .stream()
                .map(mapper::toReservaResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }
}
