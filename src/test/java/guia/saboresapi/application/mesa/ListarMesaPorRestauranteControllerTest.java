package guia.saboresapi.application.mesa;
import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.mapper.mesa.MesaMapper;
import guia.saboresapi.domain.output.mesa.MesaResponse;
import guia.saboresapi.domain.usecase.mesa.ListarMesasPorRestauranteUseCase;
import guia.saboresapi.utils.generic.JsonStringHelper;
import guia.saboresapi.utils.mesa.MesaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class ListarMesaPorRestauranteControllerTest {
  @Mock
  private MesaMapper mapper;

  @Mock
  private ListarMesasPorRestauranteUseCase listarMesaPorRestauranteUseCase;

  MockMvc mockMvc;

  AutoCloseable openMocks;

  @BeforeEach
  void setUp() {
    openMocks = MockitoAnnotations.openMocks(this);
    ListarMesasPorRestauranteController controller = new ListarMesasPorRestauranteController(mapper, listarMesaPorRestauranteUseCase);
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .addFilter((request, response, chain) -> {
          response.setCharacterEncoding("UTF-8");
          chain.doFilter(request, response);
        })
        .build();
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Test
  void devePermitirListarMesasPorRestaurante() throws Exception {
    Long restauranteId = 1L;
    Mesa mesa1 = MesaHelper.gerarMesa();
    Mesa mesa2 = MesaHelper.gerarMesa();
    Mesa mesa3 = MesaHelper.gerarMesa();
    List<Mesa> mesas = List.of(mesa1, mesa2, mesa3);

    MesaResponse mesaResponse1 = MesaHelper.gerarMesaResponse(mesa1);
    MesaResponse mesaResponse2 = MesaHelper.gerarMesaResponse(mesa2);
    MesaResponse mesaResponse3 = MesaHelper.gerarMesaResponse(mesa3);
    List<MesaResponse> listaMesaResponse = List.of(mesaResponse1, mesaResponse2, mesaResponse3);

    when(listarMesaPorRestauranteUseCase.listarMesasPorRestaurante(any(Long.class))).thenReturn(mesas);
    when(mapper.toMesaResponse(any(Mesa.class))).thenReturn(mesaResponse1, mesaResponse2, mesaResponse3);

    mockMvc.perform(
        get("/mesas/restaurante")
        .content(JsonStringHelper.asJsonString(listaMesaResponse))
        .contentType(MediaType.APPLICATION_JSON)
        .param("restauranteId", String.valueOf(restauranteId))
    )
        
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(listaMesaResponse.size()))
        .andExpect(jsonPath("$[0].id").value(mesaResponse1.id()))
        .andExpect(jsonPath("$[0].restauranteId").value(restauranteId))
        .andExpect(jsonPath("$[0].capacidade").value(mesaResponse1.capacidade()));

    verify(listarMesaPorRestauranteUseCase, times(1)).listarMesasPorRestaurante(any(Long.class));
    verify(mapper, times(3)).toMesaResponse(any(Mesa.class));

  }
}
