package guia.saboresapi.application.mesa.integracao;

import com.fiap.tc.restaurantes.domain.input.mesa.CadastrarMesaRequest;
import guia.saboresapi.utils.mesa.MesaHelper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastrarMesaControllerIT {
  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  void devePermitirCadastrarMesa() {
    CadastrarMesaRequest mesa = MesaHelper.gerarMesaCadastroRequest(9L, 4);

    given()
        .contentType("application/json")
        .body(mesa)
    .when()
        .post("/mesas")
    .then()
        .statusCode(HttpStatus.CREATED.value())
        .body(matchesJsonSchemaInClasspath("schemas/mesa/mesa.schema.json"));
  }
}
