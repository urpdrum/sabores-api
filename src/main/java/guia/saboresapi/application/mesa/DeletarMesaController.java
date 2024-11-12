package guia.saboresapi.application.mesa;


import guia.saboresapi.domain.output.mesa.MesaDeletadaResponse;
import guia.saboresapi.domain.usecase.mesa.DeletarMesaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mesas")
public class DeletarMesaController {

    private final DeletarMesaUseCase deletarMesaUseCase;

    @DeleteMapping("/{mesaId}")
    public ResponseEntity<MesaDeletadaResponse> deletarMesa(@PathVariable Long mesaId) {
        boolean mesaDeletada = deletarMesaUseCase.deletarMesa(mesaId);
        return ResponseEntity.ok(new MesaDeletadaResponse(mesaDeletada));
    }
}