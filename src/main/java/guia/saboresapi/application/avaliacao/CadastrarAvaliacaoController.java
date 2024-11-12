package guia.saboresapi.application.avaliacao;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.input.avaliacao.CadastrarAvaliacaoRequest;
import guia.saboresapi.domain.mapper.avaliacao.AvaliacaoMapper;
import guia.saboresapi.domain.output.avaliacao.AvaliacaoResponse;
import guia.saboresapi.domain.usecase.avaliacao.CadastrarAvaliacaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class CadastrarAvaliacaoController {

    private final AvaliacaoMapper avaliacaoMapper;
    private final CadastrarAvaliacaoUseCase cadastrarAvaliacaoUseCase;

    @PostMapping
    public ResponseEntity<AvaliacaoResponse> cadastrarAvaliacao(@RequestBody CadastrarAvaliacaoRequest cadastrarAvaliacaoRequest) {

        Avaliacao avaliacao = avaliacaoMapper.toAvaliacao(cadastrarAvaliacaoRequest);
        avaliacao = cadastrarAvaliacaoUseCase.cadastrarAvaliacao(avaliacao, cadastrarAvaliacaoRequest.restauranteId(), cadastrarAvaliacaoRequest.usuarioId());
        AvaliacaoResponse response = avaliacaoMapper.toAvaliacaoResponse(avaliacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
