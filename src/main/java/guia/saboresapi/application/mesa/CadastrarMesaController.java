package guia.saboresapi.application.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.input.mesa.CadastrarMesaRequest;
import guia.saboresapi.domain.mapper.mesa.MesaMapper;
import guia.saboresapi.domain.output.mesa.MesaResponse;
import guia.saboresapi.domain.usecase.mesa.CadastrarMesaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mesas")
public class CadastrarMesaController {

    private final MesaMapper mesaMapper;
    private final CadastrarMesaUseCase cadastrarMesaUseCase;

    @PostMapping
    public ResponseEntity<MesaResponse> cadastrarMesa(@RequestBody CadastrarMesaRequest cadastrarMesaRequest) {
        Mesa mesa = mesaMapper.toMesa(cadastrarMesaRequest);

        Mesa mesaCadastrada = cadastrarMesaUseCase.cadastrarMesa(mesa);

        MesaResponse mesaResponse = mesaMapper.toMesaResponse(mesaCadastrada);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(mesaResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(mesaResponse);
    }
}