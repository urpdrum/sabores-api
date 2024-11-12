package guia.saboresapi.application.mesa.integracao;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeletarMesaControllerIT {
  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  void devePermitirDeletarMesa() {
    Long id = 8L;

    when()
        .delete("/mesas/{id}", id)
    .then()
        
        .statusCode(HttpStatus.OK.value())
        .body(matchesJsonSchemaInClasspath("schemas/mesa/mesaDeletada.schema.json"));
  }

  @Test
  void deveGerarExcecao_QuandoDeletarMesa_IdDaMesaNaoExiste() {
    Long id = 800L;

    when()
        .delete("/mesas/{id}", id)
        .then()
        
        .statusCode(HttpStatus.NOT_FOUND.value())
        .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"));
  }
}
