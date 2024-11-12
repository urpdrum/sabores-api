package guia.saboresapi.application.avaliacao;


import guia.saboresapi.domain.usecase.avaliacao.DeletarAvaliacaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class DeletarAvaliacaoController {

    private final DeletarAvaliacaoUseCase deletarAvaliacaoUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarAvaliacao(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(deletarAvaliacaoUseCase.deletarAvaliacao(id));
    }
}
