package guia.saboresapi.application.restaurante.integracao;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class BuscarRestaurantePorTipoCozinhaControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveBuscarRestaurantePorTipoCozinha() {
        var tipoCozinha = "MEXICANA";

        given()
                .param("tipoCozinha", tipoCozinha)
                
        .when()
                .get("/restaurantes/tipo")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/restaurante/listarRestaurantesResponse.schema.json"));
    }
}
