package guia.saboresapi.application.mesa.integracao;

import com.fiap.tc.restaurantes.domain.input.mesa.AtualizarMesaRequest;
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
class AtualizarMesaControllerIT {
  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  void devePermitirAtualizarMesa() {
    Long id = 28L;
    AtualizarMesaRequest mesa = MesaHelper.gerarAtualizarMesaRequest(8);

    given()
        .contentType("application/json")
        .body(mesa)
        
    .when()
        .put("/mesas/{id}", id)
    .then()
        
        .statusCode(HttpStatus.OK.value())
        .body(matchesJsonSchemaInClasspath("schemas/mesa/mesa.schema.json"));
  }

  @Test
  void deveGerarExecao_QuandoAtualizarMesa_IdDaMesaNaoExiste() {
    Long id = 900L;
    AtualizarMesaRequest mesa = MesaHelper.gerarAtualizarMesaRequest(8);

    given()
        .contentType("application/json")
        .body(mesa)
    .when()
        .get("/mesas/{id}", id)
    .then()
        
        .statusCode(HttpStatus.NOT_FOUND.value())
        .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"));
  }
}
