package guia.saboresapi.application.usuario;


import guia.saboresapi.domain.entity.Usuario;
import guia.saboresapi.domain.mapper.usuario.UsuarioMapper;
import guia.saboresapi.domain.output.usuario.UsuarioResponse;
import guia.saboresapi.domain.usecase.usuario.ListarUsuariosUseCase;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class ListarUsuariosControllerTest {
  @Mock
  private ListarUsuariosUseCase listarUsuariosUseCase;

  @Mock
  private UsuarioMapper usuarioMapper;

  private MockMvc mockMvc;

  private AutoCloseable mock;

  @BeforeEach
  void setUp() {
    mock = MockitoAnnotations.openMocks(this);
    ListarUsuariosController controller = new ListarUsuariosController(usuarioMapper, listarUsuariosUseCase);
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .addFilter((request, response, chain) -> {
          response.setCharacterEncoding("UTF-8");
          chain.doFilter(request, response);
        })
        .build();
  }

  @AfterEach
  void tearDown() throws Exception {
    mock.close();
  }

  @Test
  void deveListarUsuarios() throws Exception {
    // Arrange
    Usuario usuario1 = UsuarioHelper.gerarUsuarioValido();
    Usuario usuario2 = UsuarioHelper.gerarUsuarioValido();
    Usuario usuario3 = UsuarioHelper.gerarUsuarioValido();
    List<Usuario> usuarios = List.of(usuario1, usuario2, usuario3);

    UsuarioResponse usuarioResponse1 = UsuarioHelper.gerarUsuarioResponse();
    UsuarioResponse usuarioResponse2 = UsuarioHelper.gerarUsuarioResponse();
    UsuarioResponse usuarioResponse3 = UsuarioHelper.gerarUsuarioResponse();
    List<UsuarioResponse> usuariosResponses = List.of(usuarioResponse1, usuarioResponse2, usuarioResponse3);

    when(listarUsuariosUseCase.listarUsuarios()).thenReturn(usuarios);
    when(usuarioMapper.toUsuarioResponse(any(Usuario.class))).thenReturn(usuarioResponse1, usuarioResponse2, usuarioResponse3);

    // Act & Assert
    mockMvc.perform(get("/usuarios"))
    
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.length()").value(usuariosResponses.size()))
    .andExpect(jsonPath("$[0].usuarioId").value(1L))
    .andExpect(jsonPath("$[0].nome").value("Lucas"))
    .andExpect(jsonPath("$[0].email").value("lucas@mail.com"))
    .andExpect(jsonPath("$[0].telefone").value("000000000"))
    .andExpect(jsonPath("$[0].senha").value("aA@4b7c8"));

    verify(listarUsuariosUseCase, times(1)).listarUsuarios();
    verify(usuarioMapper, times(3)).toUsuarioResponse(any(Usuario.class));
  }

}
