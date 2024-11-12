package guia.saboresapi.application.usuario;

import com.fiap.tc.restaurantes.application.handler.GlobalExceptionHandler;
import com.fiap.tc.restaurantes.domain.entity.Usuario;
import com.fiap.tc.restaurantes.domain.exception.usuario.UsuarioNotFoundException;
import com.fiap.tc.restaurantes.domain.mapper.usuario.UsuarioMapper;
import com.fiap.tc.restaurantes.domain.output.usuario.UsuarioResponse;
import com.fiap.tc.restaurantes.domain.usecase.usuario.BuscarUsuarioPorIdUseCase;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BuscarUsuarioPorIdControllerTest {
  @Mock
  private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

  @Mock
  private UsuarioMapper usuarioMapper;

  private MockMvc mockMvc;

  private AutoCloseable mock;

  @BeforeEach
  void setUp() {
    mock = MockitoAnnotations.openMocks(this);
    BuscarUsuarioPorIdController controller = new BuscarUsuarioPorIdController(usuarioMapper, buscarUsuarioPorIdUseCase);
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(new GlobalExceptionHandler())
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
  void deveBuscarUsuarioPorId() throws Exception {
    // Arrange
    Long id = 1L;
    Usuario usuario = UsuarioHelper.gerarUsuarioValidoComId();
    UsuarioResponse usuarioResponse = UsuarioHelper.gerarUsuarioResponse();

    when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(any(Long.class))).thenReturn(usuario);
    when(usuarioMapper.toUsuarioResponse(usuario)).thenReturn(usuarioResponse);

    // Act & Arrange
    mockMvc.perform(get("/usuarios/{id}", id))
        
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.usuarioId").value(id))
        .andExpect(jsonPath("$.nome").value(usuarioResponse.nome()))
        .andExpect(jsonPath("$.email").value(usuarioResponse.email()))
        .andExpect(jsonPath("$.telefone").value(usuarioResponse.telefone()))
        .andExpect(jsonPath("$.senha").value(usuarioResponse.senha()));

  }

  @Test
  void deveGerarExcecao_QuandoBuscarUsuario_IdNaoExiste() throws Exception {
    // Arrange
    Long id = 100L;

    when(buscarUsuarioPorIdUseCase.buscarUsuarioPorId(any(Long.class))).thenThrow(new UsuarioNotFoundException("Usuário de id: " + id + " não encontrado."));
    when(usuarioMapper.toUsuarioResponse(any(Usuario.class))).thenReturn(null);

    // Act & Assert
    mockMvc.perform(get("/usuarios/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
        )
        
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.erro").value("Usuário de id: " + id + " não encontrado."))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.horario").exists())
        .andExpect(jsonPath("$.rota").value("/usuarios/" + id));

    verify(buscarUsuarioPorIdUseCase, times(1)).buscarUsuarioPorId(any(Long.class));
    verify(usuarioMapper, never()).toUsuarioResponse(null);
  }

}
