package guia.saboresapi.application.restaurante;

import guia.saboresapi.domain.mapper.restaurante.RestauranteMapper;
import guia.saboresapi.domain.output.restaurante.RestauranteResponse;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorLocalidadeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class BuscarRestaurantePorLocalidadeController {

    private final RestauranteMapper restauranteMapper;
    private final BuscarRestaurantePorLocalidadeUseCase buscarRestaurantePorLocalidadeUseCase;

    @GetMapping("/localidade")
    public ResponseEntity<List<RestauranteResponse>> buscarRestaurantesPorLocalidade(@RequestParam String localidade) {
        List<RestauranteResponse> listaRestaurantePorLocalidade = buscarRestaurantePorLocalidadeUseCase.buscarRestaurantePorLocalidade(localidade)
                .stream()
                .map(restauranteMapper::toRestauranteResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(listaRestaurantePorLocalidade);
    }
}
