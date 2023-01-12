package de.tech26.robotfactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test-endpoints.properties")
public class OrderARobotAcceptanceTest {

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
                "" +
                        "   { " +
                        "      \"productType\":\"ROBOT\", " +
                        "      \"components\": [\"I\",\"A\",\"D\",\"F\"] " +
                        "   }"
        )
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .body("order_id", notNullValue())
                .body("total", equalTo(160.11f));
    }

    @Test
    public void should_not_allow_invalid_body() {
        postOrder(
                "  {    \n" +
                        "      \"productType\":\"ROBOT\", " +
                        "   \"components\": \"BENDER\"\n" +
                        "}"
        )
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void should_not_allow_invalid_robot_configuration() {
        postOrder(
                "{\n" +
                        "  \"productType\":\"ROBOT\", " +
                        "  \"components\": [\"A\", \"C\", \"I\", \"D\"]\n" +
                        " }"
        )
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    private ValidatableResponse postOrder(String body) {
        return RestAssured.given()
                .log()
                .all()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post("/orders")
                .then()
                .log()
                .all();
    }
}
