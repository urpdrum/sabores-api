package guia.saboresapi.application.avaliacao;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.input.avaliacao.AtualizarAvaliacaoRequest;
import guia.saboresapi.domain.mapper.avaliacao.AvaliacaoMapper;
import guia.saboresapi.domain.output.avaliacao.AvaliacaoResponse;
import guia.saboresapi.domain.usecase.avaliacao.AtualizarAvaliacaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AtualizarAvaliacaoController {

    private final AvaliacaoMapper avaliacaoMapper;
    private final AtualizarAvaliacaoUseCase atualizarAvaliacaoUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse> atualizarAvaliacao(@PathVariable("id") Long id,
                                                                @RequestBody AtualizarAvaliacaoRequest atualizarAvaliacaoRequest) {
        Avaliacao avaliacao = avaliacaoMapper.toAvaliacao(atualizarAvaliacaoRequest);

        AvaliacaoResponse avaliacaoResponse = avaliacaoMapper.toAvaliacaoResponse(atualizarAvaliacaoUseCase.atualizarAvaliacao(id, avaliacao));

        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoResponse);
    }
}
