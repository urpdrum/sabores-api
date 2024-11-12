package guia.saboresapi.application.restaurante;

import guia.saboresapi.domain.mapper.restaurante.RestauranteMapper;
import guia.saboresapi.domain.output.restaurante.RestauranteResponse;
import guia.saboresapi.domain.usecase.restaurante.BuscarRestaurantePorTipoCozinhaUseCase;
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
public class BuscarRestaurantePorTipoCozinhaController {

    private final RestauranteMapper restauranteMapper;
    private final BuscarRestaurantePorTipoCozinhaUseCase buscarRestaurantePorTipoCozinhaUseCase;

    @GetMapping("/tipo")
    public ResponseEntity<List<RestauranteResponse>> buscarRestaurantesPorTipoCozinha(@RequestParam String tipoCozinha) {
        List<RestauranteResponse> listaRestaurantePorTipoCozinha = buscarRestaurantePorTipoCozinhaUseCase.buscarPorTipoCozinha(tipoCozinha)
                .stream()
                .map(restauranteMapper::toRestauranteResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(listaRestaurantePorTipoCozinha);
    }
}
