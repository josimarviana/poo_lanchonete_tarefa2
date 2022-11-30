package br.com.appdahora.lanchonete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

@SpringBootTest
public class ClienteControllerTests {

    @LocalServerPort
    private int port=8080;

    //@Test
    public void deveRetornarStatus200_QuandoConsultarClientes() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath("/clientes")
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
