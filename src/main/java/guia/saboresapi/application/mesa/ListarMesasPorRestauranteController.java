package guia.saboresapi.application.mesa;


import guia.saboresapi.domain.mapper.mesa.MesaMapper;
import guia.saboresapi.domain.output.mesa.MesaResponse;
import guia.saboresapi.domain.usecase.mesa.ListarMesasPorRestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mesas")
public class ListarMesasPorRestauranteController {

    private final MesaMapper mesaMapper;
    private final ListarMesasPorRestauranteUseCase listarMesasPorRestauranteUseCase;

    @GetMapping("/restaurante")
    public ResponseEntity<List<MesaResponse>> listarMesas(@RequestParam Long restauranteId) {
        List<MesaResponse> listarMesas = listarMesasPorRestauranteUseCase.listarMesasPorRestaurante(restauranteId)
                .stream()
                .map(mesaMapper::toMesaResponse)
                .toList();

        return ResponseEntity.ok(listarMesas);
    }
}