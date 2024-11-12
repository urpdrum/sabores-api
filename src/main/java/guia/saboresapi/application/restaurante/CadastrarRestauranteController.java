package guia.saboresapi.application.restaurante;

import guia.saboresapi.domain.entity.Restaurante;
import guia.saboresapi.domain.input.restaurante.CadastrarRestauranteRequest;
import guia.saboresapi.domain.mapper.restaurante.RestauranteMapper;
import guia.saboresapi.domain.output.restaurante.RestauranteResponse;
import guia.saboresapi.domain.usecase.restaurante.CadastrarRestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class CadastrarRestauranteController {

    private final RestauranteMapper restauranteMapper;
    private final CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @PostMapping
    public ResponseEntity<RestauranteResponse> cadastrarRestaurante(@RequestBody CadastrarRestauranteRequest request) {

        Restaurante restaurante = restauranteMapper.toRestaurante(request);

        RestauranteResponse restauranteResponse = restauranteMapper.toRestauranteResponse(cadastrarRestauranteUseCase.cadastrarRestaurante(restaurante));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(restauranteResponse.restauranteId())
                .toUri();
        return ResponseEntity.created(uri).body(restauranteResponse);
    }
}
