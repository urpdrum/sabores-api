package guia.saboresapi.application.usuario;

import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.input.usuario.AtualizarUsuarioRequest;
import guia.saboresapi.domain.mapper.usuario.UsuarioMapper;
import guia.saboresapi.domain.output.usuario.UsuarioResponse;
import guia.saboresapi.domain.usecase.usuario.AtualizarUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class AtualizarUsuarioController {

    private final UsuarioMapper usuarioMapper;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable Long id,
                                                            @RequestBody AtualizarUsuarioRequest usuarioRequest) {
        Usuario usuarioAtualizado = atualizarUsuarioUseCase.atualizarUsuario(id, usuarioMapper.toUsuario(usuarioRequest));
        UsuarioResponse usuarioResponse = usuarioMapper.toUsuarioResponse(usuarioAtualizado);

        return ResponseEntity.ok(usuarioResponse);
    }
}
