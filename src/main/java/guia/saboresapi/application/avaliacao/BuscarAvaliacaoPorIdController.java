package guia.saboresapi.application.avaliacao;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.mapper.avaliacao.AvaliacaoMapper;
import guia.saboresapi.domain.output.avaliacao.AvaliacaoResponse;
import guia.saboresapi.domain.usecase.avaliacao.BuscarAvaliacaoPorIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class BuscarAvaliacaoPorIdController {

    private final AvaliacaoMapper avaliacaoMapper;
    private final BuscarAvaliacaoPorIdUseCase buscarAvaliacaoPorIdUseCase;

    @GetMapping("{id}")
    public ResponseEntity<AvaliacaoResponse> buscarAvaliacaoPorId(@PathVariable Long id) {
        Avaliacao avaliacao = buscarAvaliacaoPorIdUseCase.buscarAvaliacaoPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoMapper.toAvaliacaoResponse(avaliacao));
    }
}
