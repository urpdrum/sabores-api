package guia.saboresapi.application.mesa;

import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.input.mesa.AtualizarMesaRequest;
import guia.saboresapi.domain.mapper.mesa.MesaMapper;
import guia.saboresapi.domain.output.mesa.MesaResponse;
import guia.saboresapi.domain.usecase.mesa.AtualizarMesaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mesas")
public class AtualizarMesaController {

    private final MesaMapper mesaMapper;
    private final AtualizarMesaUseCase atualizarMesaUseCase;

    public AtualizarMesaController(MesaMapper mesaMapper, AtualizarMesaUseCase atualizarMesaUseCase) {
        this.mesaMapper = mesaMapper;
        this.atualizarMesaUseCase = atualizarMesaUseCase;
    }

    @PutMapping("/{mesaId}")
    public ResponseEntity<MesaResponse> atualizarMesa(@PathVariable Long mesaId,
                                                      @RequestBody AtualizarMesaRequest mesaRequest) {
        Mesa mesaAtualizada = atualizarMesaUseCase.atualizarMesa(mesaId, mesaMapper.toMesa(mesaRequest));
        MesaResponse mesaResponse = mesaMapper.toMesaResponse(mesaAtualizada);

        return ResponseEntity.ok(mesaResponse);
    }
}