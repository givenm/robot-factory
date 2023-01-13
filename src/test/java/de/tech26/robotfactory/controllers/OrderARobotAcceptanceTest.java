package de.tech26.robotfactory.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tech26.robotfactory.dto.responses.CreateOrderResponse;
import de.tech26.robotfactory.dto.responses.GetOrderResponse;
import de.tech26.robotfactory.dto.responses.GetOrdersResponse;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.hamcrest.core.IsNull.notNullValue;


public class OrderARobotAcceptanceTest extends ControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost" + contextPath;
        RestAssured.port = springBootPort;
    }

    @Test
    public void should_order_a_robot() {
        postRequest("/orders",
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
    public void should_new_robot_on_find_all_robot_orders() throws JsonProcessingException {
        postRequest("/orders",
                "" +
                        "   { " +
                        "      \"productType\":\"ROBOT\", " +
                        "      \"components\": [\"I\",\"A\",\"E\",\"G\"] " +
                        "   }"
        );
        ValidatableResponse res = getRequest("/orders")
                .assertThat()
                .statusCode(HttpStatus.OK.value());
        GetOrdersResponse ordersResponse = objectMapper.readValue(res.extract().asString(), GetOrdersResponse.class);
        assertThat(ordersResponse.getOrders()).hasAtLeastOneElementOfType(GetOrderResponse.class);
        assertThat(ordersResponse.getOrders().stream().anyMatch(order -> {
            return order.getProductIds().containsAll(Arrays.asList("I", "A", "E", "G")) &&
                    order.getTotal().equals(new BigDecimal("167.92")) &&
                    order.getOrderedAt() != null &&
                    order.getCustomerId() != null &&
                    order.getId() != null;
        })).isTrue();

    }

    @Test
    public void should_find_a_robot_order_by_id() throws JsonProcessingException {
        ValidatableResponse responseWithId = postRequest("/orders",
                "" +
                        "   { " +
                        "      \"productType\":\"ROBOT\", " +
                        "      \"components\": [\"J\",\"B\",\"E\",\"G\"] " +
                        "   }"
        )
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
        CreateOrderResponse newOrderResponse = objectMapper.readValue(responseWithId.extract().asString(), CreateOrderResponse.class);

        ValidatableResponse res = getRequest("/orders/" + newOrderResponse.getOrderId())
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(newOrderResponse.getOrderId()))
                .body("total", equalTo(173.90f))
                .body("customerId", notNullValue())
                .body("orderedAt", notNullValue())
                .body("productIds", hasItems("J", "B", "E", "G"));
    }

    @Test
    public void should_not_allow_invalid_body() {
        postRequest("/orders",
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
        postRequest("/orders",
                "{\n" +
                        "  \"productType\":\"ROBOT\", " +
                        "  \"components\": [\"A\", \"C\", \"I\", \"D\"]\n" +
                        " }"
        )
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

}
