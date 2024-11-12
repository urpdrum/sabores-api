package guia.saboresapi.bdd;

import com.fiap.tc.restaurantes.domain.entity.Usuario;
import com.fiap.tc.restaurantes.domain.input.avaliacao.AtualizarAvaliacaoRequest;
import com.fiap.tc.restaurantes.domain.input.mesa.AtualizarMesaRequest;
import com.fiap.tc.restaurantes.domain.input.mesa.CadastrarMesaRequest;
import com.fiap.tc.restaurantes.domain.input.restaurante.AtualizarRestauranteRequest;
import com.fiap.tc.restaurantes.domain.input.restaurante.CadastrarRestauranteRequest;
import com.fiap.tc.restaurantes.domain.input.usuario.AtualizarUsuarioRequest;
import com.fiap.tc.restaurantes.domain.input.usuario.CadastrarUsuarioRequest;
import com.fiap.tc.restaurantes.domain.output.avaliacao.AvaliacaoResponse;
import com.fiap.tc.restaurantes.domain.output.mesa.MesaResponse;
import com.fiap.tc.restaurantes.domain.output.reserva.ReservaResponse;
import com.fiap.tc.restaurantes.domain.output.restaurante.RestauranteResponse;
import com.fiap.tc.restaurantes.domain.output.usuario.UsuarioResponse;
import guia.saboresapi.utils.avaliacao.AvaliacaoHelper;
import guia.saboresapi.utils.mesa.MesaHelper;
import guia.saboresapi.utils.reserva.ReservaHelper;
import guia.saboresapi.utils.restaurante.RestauranteHelper;
import guia.saboresapi.utils.usuario.UsuarioHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDefinition {
  private Response response;
  private UsuarioResponse usuarioResponse;
  private final String ENDPOINT_API_USUARIOS = "http://localhost:8080/usuarios";
  private RestauranteResponse restauranteResponse;
  private final String ENDPOINT_API_RESTAURANTES = "http://localhost:8080/restaurantes";
  private ReservaResponse reservaResponse;
  private final String ENDPOINT_API_RESERVAS = "http://localhost:8080/reservas";
  private MesaResponse mesaResponse;
  private final String ENDPOINT_API_MESAS = "http://localhost:8080/mesas";
  private AvaliacaoResponse avaliacaoResponse;
  private final String ENDPOINT_API_AVALIACAO = "http://localhost:8080/avaliacoes";

  // USUÁRIO
  @Quando("registrar um novo usuario")
  public UsuarioResponse registrar_um_novo_usuario() {
    CadastrarUsuarioRequest request = UsuarioHelper.gerarCadastrarUsuarioRequest();

    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post(ENDPOINT_API_USUARIOS);

    return response.then().extract().as(UsuarioResponse.class);
  }

  @Então("o usuario é registrado com sucesso")
  public void o_usuario_é_registrado_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.CREATED.value());
  }

  @Então("o usuario deve ser apresentado")
  public void o_usuario_deve_ser_apresentado() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/usuario/usuario.schema.json"));
  }

  @Dado("que um usuario ja esta cadastrado")
  public void que_um_usuario_ja_esta_cadastrado() {
    usuarioResponse = registrar_um_novo_usuario();
  }

  @Quando("efetuar a busca de usuario")
  public void efetuar_a_busca_de_usuario() {
    response = when()
        .get(ENDPOINT_API_USUARIOS + "/{id}", usuarioResponse.usuarioId());
  }

  @Então("o usuario é exibido com sucesso")
  public void o_usuario_é_exibido_com_sucesso() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/usuario/usuario.schema.json"));
  }

  @Então("efeturar a requisição para atualizar o usuario")
  public void efeturar_a_requisição_para_atualizar_o_usuario() {
    Usuario usuario = UsuarioHelper.gerarUsuarioValido();
    usuario.setNome("João");
    usuario.setSenha("Senha@98752");
    usuario.setTelefone("00000000000");

    AtualizarUsuarioRequest request = new AtualizarUsuarioRequest(
        usuario.getNome(),
        usuario.getEmail(),
        usuario.getSenha(),
        usuario.getTelefone()
    );

    response =
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .put(ENDPOINT_API_USUARIOS + "/{id}", usuarioResponse.usuarioId());
  }

  @Então("o usuario é atualizado com sucesso")
  public void o_usuario_é_atualizado_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Quando("requisitar a remoção do usuario")
  public void requisitar_a_remoção_do_usuario() {
    response = when()
        .delete(ENDPOINT_API_USUARIOS + "/{id}", usuarioResponse.usuarioId());
  }

  @Então("o usuario é removido com sucesso")
  public void o_usuario_é_removido_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Então("deve apresentar o resultado da remoção")
  public void deve_apresentar_o_resultado_da_remoção() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/usuario/usuarioDeletado.schema.json"));
  }

  // RESTAURANTE
  @Quando("cadastrar um novo restaurante")
  public RestauranteResponse cadastrar_um_novo_restaurante() {
    CadastrarRestauranteRequest request = RestauranteHelper.gerarCadastrarRestauranteRequest();

    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post(ENDPOINT_API_RESTAURANTES);

    return response.then().extract().as(RestauranteResponse.class);
  }

  @Então("o restaurante é registrado com sucesso")
  public void o_restaurante_é_registrado_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.CREATED.value());
  }

  @Então("o restaurante deve ser apresentado")
  public void o_restaurante_deve_ser_apresentado() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/restaurante/restaurante.schema.json"));
  }

  @Dado("que um restaurante já está cadastrado")
  public void que_um_restaurante_já_está_cadastrado() {
    restauranteResponse = cadastrar_um_novo_restaurante();
  }

  @Quando("efetuar a busca de restaurante")
  public void efetuar_a_busca_de_restaurante() {
    response = when()
        .get(ENDPOINT_API_RESTAURANTES + "/{id}", restauranteResponse.restauranteId());
  }

  @Então("o restaurante é exibido com sucesso")
  public void o_restaurante_é_exibido_com_sucesso() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/restaurante/restaurante.schema.json"));
  }

  @Então("efeturar a requisição para atualizar o restaurante")
  public void efeturar_a_requisição_para_atualizar_o_restaurante() {
    AtualizarRestauranteRequest request = RestauranteHelper.gerarAtualizarRestauranteRequest();

    response =
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .put(ENDPOINT_API_RESTAURANTES + "/{id}", restauranteResponse.restauranteId());
  }

  @Então("o restaurante é atualizado com sucesso")
  public void o_restaurante_é_atualizado_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Quando("requisitar a remoção do restaurante")
  public void requisitar_a_remoção_do_restaurante() {
    response = when()
        .delete(ENDPOINT_API_RESTAURANTES + "/{id}", restauranteResponse.restauranteId());
  }

  @Então("o restaurante é removido com sucesso")
  public void o_restaurante_é_removido_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Então("deve apresentar o resultado da remoção do restaurante")
  public void deve_apresentar_o_resultado_da_remoção_do_restaurante() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/restaurante/restauranteDeletadoResponse.schema.json"));
  }

  // RESERVA
  @Quando("cadastrar uma reserva")
  public ReservaResponse cadastrar_uma_reserva() {
    var request = ReservaHelper.gerarCadastrarReservaRequest();
    if (mesaResponse != null) {
      request = ReservaHelper.gerarCadastrarReservaRequest(mesaResponse.id());
    }

    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post(ENDPOINT_API_RESERVAS);

    return response.then().extract().as(ReservaResponse.class);
  }

  @Então("a reserva é registrada com sucesso")
  public void a_reserva_é_registrada_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Então("a reserva deve ser apresentada")
  public void a_reserva_deve_ser_apresentada() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaResponse.schema.json"));
  }

  @Dado("que a reserva já está cadastrada")
  public void que_a_reserva_já_está_cadastrada() {
    reservaResponse = cadastrar_uma_reserva();
  }

  @Quando("efetuar a busca da reserva")
  public void efetuar_a_busca_da_reserva() {
    response = when()
        .get(ENDPOINT_API_RESERVAS + "/{id}", reservaResponse.reservaId());
  }

  @Então("efeturar a requisição para atualizar a reserva")
  public void efeturar_a_requisição_para_atualizar_a_reserva() {
    var request = ReservaHelper.gerarAtualizarReservaRequest();

    response =
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .put(ENDPOINT_API_RESERVAS + "/{id}", reservaResponse.reservaId());
  }

  @Então("a reserva é atualizada com sucesso")
  public void a_reserva_é_atualizada_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Quando("requisitar a remoção da reserva")
  public void requisitar_a_remoção_da_reserva() {
    response = when()
        .delete(ENDPOINT_API_RESERVAS + "/{id}", reservaResponse.reservaId());
  }

  @Então("a reserva é removida com sucesso")
  public void a_reserva_é_removida_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Então("deve apresentar o resultado da remoção de reserva")
  public void deve_apresentar_o_resultado_da_remoção_de_reserva() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaDeletadaResponse.schema.json"));
  }

  // MESA
  @Quando("cadastrar uma mesa para o restaurante")
  public MesaResponse cadastrar_uma_mesa_para_o_restaurante() {
    CadastrarMesaRequest request = MesaHelper.gerarMesaCadastroRequest(1L, 8);
    if (restauranteResponse != null) {
      request = MesaHelper.gerarMesaCadastroRequest(restauranteResponse.restauranteId(), 8);
    }

    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post(ENDPOINT_API_MESAS);

    return response.then().extract().as(MesaResponse.class);
  }

  @Então("a mesa é registrada com sucesso")
  public void a_mesa_é_registrada_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.CREATED.value());
  }

  @Então("a mesa deve ser apresentada")
  public void a_mesa_deve_ser_apresentada() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/mesa/mesa.schema.json"));
  }

  @Dado("que a mesa já está cadastrada")
  public void que_a_mesa_já_está_cadastrada() {
    mesaResponse = cadastrar_uma_mesa_para_o_restaurante();
  }

  @Quando("efetuar a busca da mesa de um restaurante")
  public void efetuar_a_busca_da_mesa_de_um_restaurante() {
    Long restauranteId = 9L;

    response = when()
        .get(ENDPOINT_API_MESAS + "/restaurante?restauranteId=" + restauranteId);
  }

  @Então("as mesas são exibida")
  public void as_mesas_são_exibida() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/mesa/listarMesasPorRestaurante.schema.json"));
  }

  @Então("efeturar a requisição para atualizar a mesa")
  public void efeturar_a_requisição_para_atualizar_a_mesa() {
    AtualizarMesaRequest request = MesaHelper.gerarAtualizarMesaRequest(8);

    response =
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .put(ENDPOINT_API_MESAS + "/{id}", mesaResponse.id());
  }

  @Então("a mesa é atualizada com sucesso")
  public void a_mesa_é_atualizada_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Dado("que uma mesa já está cadastrada")
  public void que_uma_mesa_já_está_cadastrada() {
    mesaResponse = cadastrar_uma_mesa_para_o_restaurante();
  }

  @Quando("requisitar a remoção da mesa")
  public void requisitar_a_remoção_da_mesa() {
    response = when()
        .delete(ENDPOINT_API_MESAS + "/{id}", mesaResponse.id());
  }

  @Então("a mesa é removida com sucesso")
  public void a_mesa_é_removida_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Então("deve apresentar o resultado da remoção da mesa")
  public void deve_apresentar_o_resultado_da_remoção_da_mesa() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/mesa/mesaDeletada.schema.json"));
  }

  // AVALIAÇÃO
  @Quando("enviar uma avaliação para um Restaurante")
  public AvaliacaoResponse enviar_uma_avaliação_para_um_restaurante() {
    var request = AvaliacaoHelper.gerarCadastrarAvaliacaoRequest();

    response = given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .post(ENDPOINT_API_AVALIACAO);

    return response.then().extract().as(AvaliacaoResponse.class);
  }

  @Então("a avaliação é registrada com sucesso")
  public void a_avaliação_é_registrada_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.CREATED.value());
  }

  @Então("a avaliacao deve ser apresentada")
  public void a_avaliacao_deve_ser_apresentada() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/avaliacao/avaliacaoResponse.schema.json"));
  }

  @Quando("efetuar a busca de avaliação de restaurante")
  public void efetuar_a_busca_de_avaliação_de_restaurante() {
    Long restauranteId = 9L;

    response = when()
        .get(ENDPOINT_API_AVALIACAO + "/restaurante?restauranteId=" + restauranteId);
  }

  @Então("o restaurante e a avaliação são exibidos")
  public void o_restaurante_e_a_avaliação_são_exibidos() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/avaliacao/avaliacaoResponseList.schema.json"));
  }

  @Dado("que uma avaliacao já está cadastrada")
  public void que_uma_avaliacao_já_está_cadastrada() {
    avaliacaoResponse = enviar_uma_avaliação_para_um_restaurante();
  }

  @Então("efeturar a requisição para atualizar a avaliação")
  public void efeturar_a_requisição_para_atualizar_a_avaliação() {
    AtualizarAvaliacaoRequest request = AvaliacaoHelper.gerarAtualizarAvaliacaoRequest();

    response =
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .put(ENDPOINT_API_AVALIACAO + "/{id}", avaliacaoResponse.avaliacaoId());
  }

  @Então("a avaliação é atualizada com sucesso")
  public void a_avaliação_é_atualizada_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Quando("requisitar a remoção da avaliação")
  public void requisitar_a_remoção_da_avaliação() {
    response = when()
        .delete(ENDPOINT_API_AVALIACAO + "/{id}", avaliacaoResponse.avaliacaoId());
  }

  @Então("a avaliação é removida com sucesso")
  public void a_avaliação_é_removida_com_sucesso() {
    response.then()
        .statusCode(HttpStatus.OK.value());
  }

  @Então("deve apresentar o resultado da remoção da avaliacao")
  public void deve_apresentar_o_resultado_da_remoção_da_avaliacao() {
    response.then()
        .body(matchesJsonSchemaInClasspath("schemas/avaliacao/avaliacaoDeletadaResponse.schema.json"));
  }
}
