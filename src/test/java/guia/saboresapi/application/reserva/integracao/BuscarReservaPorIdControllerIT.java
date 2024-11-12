package guia.saboresapi.application.reserva.integracao;

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
class BuscarReservaPorIdControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirBuscarReservaPorId() {
        var id = 1L;

        given()
                
        .when()
                .get("/reservas/{id}", id)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaResponse.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoBuscarReservaPorId_IdNaoEncontrado() {
        var id = 145616L;

        given()
                
        .when()
                .get("/reservas/{id}", id)
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }
}
