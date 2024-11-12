package guia.saboresapi.application.restaurante;

import guia.saboresapi.domain.mapper.restaurante.RestauranteMapper;
import guia.saboresapi.domain.usecase.restaurante.DeletarRestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class DeletarRestauranteController {

    private final RestauranteMapper restauranteMapper;
    private final DeletarRestauranteUseCase deletarRestauranteUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarRestaurante(@PathVariable Long id) {
        Boolean foiDeletado = deletarRestauranteUseCase.deletarRestaurante(id);
        return ResponseEntity.status(HttpStatus.OK).body(foiDeletado);
    }
}
