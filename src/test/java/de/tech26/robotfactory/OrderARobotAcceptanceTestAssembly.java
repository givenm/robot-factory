package de.tech26.robotfactory;

import de.tech26.robotfactory.controllers.OrdersController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test-endpoints.properties")
public class OrderARobotAcceptanceTestAssembly {

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @LocalServerPort
    private int springBootPort;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost" + contextPath;
        RestAssured.port = springBootPort;
    }

    @Test
    public void should_order_a_robot() {
        postOrder(
                "{ \n" +
                        "                            \"components\": [\"I\",\"A\",\"D\",\"F\"]\n" +
                        "   }"
        ).then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .body("order_id", notNullValue())
                .body("total", equalTo(160.11f));
    }

    @Test
    public void should_not_allow_invalid_body() {
        postOrder(
                "  {    \n" +
                        "   \"components\": \"BENDER\"\n" +
                        "}"
        ).then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void should_not_allow_invalid_robot_configuration() {
        postOrder(
                "{\n" +
                        "  \"components\": [\"A\", \"C\", \"I\", \"D\"]\n" +
                        " }"
        ).then()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    private Response postOrder(String body) {
        return RestAssured.given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post("/orders");
    }
}
