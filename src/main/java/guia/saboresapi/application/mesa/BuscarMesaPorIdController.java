package guia.saboresapi.application.mesa;

import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.mapper.mesa.MesaMapper;
import guia.saboresapi.domain.output.mesa.MesaResponse;
import guia.saboresapi.domain.usecase.mesa.BuscarMesaPorIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mesas")
public class BuscarMesaPorIdController {

    private final MesaMapper mesaMapper;
    private final BuscarMesaPorIdUseCase buscarMesaPorIdUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<MesaResponse> buscarMesa(@PathVariable Long id) {
        Mesa mesaBuscada = buscarMesaPorIdUseCase.buscarMesaPorId(id);
        return ResponseEntity.ok(mesaMapper.toMesaResponse(mesaBuscada));
    }
}