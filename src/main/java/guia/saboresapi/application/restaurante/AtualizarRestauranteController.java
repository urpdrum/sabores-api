package guia.saboresapi.application.restaurante;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.input.restaurante.AtualizarRestauranteRequest;
import guia.saboresapi.domain.mapper.restaurante.RestauranteMapper;
import guia.saboresapi.domain.output.restaurante.RestauranteResponse;
import guia.saboresapi.domain.usecase.restaurante.AtualizarRestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class AtualizarRestauranteController {

    private final RestauranteMapper restauranteMapper;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponse> atualizarRestaurante(@PathVariable Long id, @RequestBody AtualizarRestauranteRequest request) {
        Restaurante restaurante = restauranteMapper.toRestaurante(request);

        RestauranteResponse restauranteResponse = restauranteMapper.toRestauranteResponse(atualizarRestauranteUseCase.atualizarRestaurante(id, restaurante));

        return ResponseEntity.status(HttpStatus.OK).body(restauranteResponse);
    }
}
