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
class ListarMesasPorRestauranteControllerIT {
  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  void deveListarMesasPorRestaurante() {
    Long restauranteId = 9L;

    when()
        .get("/mesas/restaurante?restauranteId=" + restauranteId)
    .then()
        
        .statusCode(HttpStatus.OK.value())
        .body(matchesJsonSchemaInClasspath("schemas/mesa/listarMesasPorRestaurante.schema.json"));
  }
}
