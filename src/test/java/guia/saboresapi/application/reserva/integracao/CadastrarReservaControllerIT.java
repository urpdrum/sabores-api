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
class CadastrarReservaControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void devePermitirCadastrarReserva() {
        var request = ReservaHelper.gerarCadastrarReservaRequest();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/reservas")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaResponse.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_UsuarioNaoEncontrado() {
        var request = ReservaHelper.gerarCadastrarReservaRequestComUsuarioNaoEncontrado();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/reservas")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_MesaNaoEncontrada() {
        var request = ReservaHelper.gerarCadastrarReservaRequestComMesaNaoEncontrada();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/reservas")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataInicioAntesDeHoje() {
        var request = ReservaHelper.gerarCadastrarReservaRequestComDataInicioAntesDeHoje();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/reservas")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataFimAntesDeHoje() {
        var request = ReservaHelper.gerarCadastrarReservaRequestComDataFimAntesDeHoje();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/reservas")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_DataInicioMaiorQueDataFim() {
        var request = ReservaHelper.gerarCadastrarReservaRequestComDataFimAntesDeDataInicio();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/reservas")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }

    @Test
    void deveGerarExcecao_QuandoCadastrarReserva_MesaJaReservada() {
        var request = ReservaHelper.gerarCadastrarReservaRequestComMesaJaReservada();

        given()
                .contentType("application/json")
                .body(request)
                
        .when()
                .post("/reservas")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/exception/erroCustomizado.schema.json"))
                ;
    }
}
