package de.tech26.robotfactory.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test-endpoints.properties")
public class ControllerTest {

    @Value("${server.servlet.context-path}")
    protected String contextPath;
    @LocalServerPort
    protected int springBootPort;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost" + contextPath;
        RestAssured.port = springBootPort;
    }

    protected ValidatableResponse postRequest(String path, String body) {
        return RestAssured.given()
                .log()
                .all()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(path)
                .then()
                .log()
                .all();
    }

    protected ValidatableResponse  getRequest(String path) {
        return RestAssured.given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .log()
                .all();
    }

}
