package guia.saboresapi.application.reserva.integracao;

import guia.saboresapi.utils.reserva.ReservaHelper;
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
class AtualizarReservaControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirAtualizarReserva() {
        var id = 2L;
        var request = ReservaHelper.gerarAtualizarReservaRequest();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/reservas/{id}", id)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaResponse.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_IdNaoEncontrado() {
        var id = 12345678L;
        var request = ReservaHelper.gerarAtualizarReservaRequest();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/reservas/{id}", id)
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataInicioAntesDeHoje() {
        var id = 2L;
        var request = ReservaHelper.gerarAtualizarReservaRequestComDataInicioAntesDeHoje();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/reservas/{id}", id)
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataFimAntesDeHoje() {
        var id = 2L;
        var request = ReservaHelper.gerarAtualizarReservaRequestComDataFimAntesDeHoje();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/reservas/{id}", id)
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_DataInicioMaiorQueDataFim() {
        var id = 2L;
        var request = ReservaHelper.gerarAtualizarReservaRequestComDataFimAntesDataInicio();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/reservas/{id}", id)
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoAtualizarReserva_MesaJaReservada() {
        var id = 2L;
        var request = ReservaHelper.gerarAtualizarReservaRequestComMesaJaReservada();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .put("/reservas/{id}", id)
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }
}
