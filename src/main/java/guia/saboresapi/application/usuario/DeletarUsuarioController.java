package guia.saboresapi.application.usuario;


import guia.saboresapi.domain.output.usuario.UsuarioDeletadoResponse;
import guia.saboresapi.domain.usecase.usuario.DeletarUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class DeletarUsuarioController {
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDeletadoResponse> deletarUsuario(@PathVariable Long id) {
        boolean usuarioFoiDeletado = deletarUsuarioUseCase.deletarUsuario(id);
        return ResponseEntity.ok(new UsuarioDeletadoResponse(usuarioFoiDeletado));
    }
}
