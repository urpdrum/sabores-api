package guia.saboresapi.application.reserva;


import guia.saboresapi.domain.usecase.reserva.DeletarReservaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class DeletarReservaController {

    private final DeletarReservaUseCase deletarReservaUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(deletarReservaUseCase.deletarReserva(id));
    }
}
