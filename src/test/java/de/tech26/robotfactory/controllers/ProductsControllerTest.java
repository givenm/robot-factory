package de.tech26.robotfactory.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.tech26.robotfactory.dto.responses.GetProductResponse;
import de.tech26.robotfactory.dto.responses.GetProductsResponse;
import de.tech26.robotfactory.enums.ProductGroupEnum;
import de.tech26.robotfactory.enums.RobotPartType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProductsControllerTest extends ControllerTest {

    @Test
    public void should_find_all_products() {
        GetProductsResponse productsResponse = getRequest("/products")
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(GetProductsResponse.class);
        assertThat(productsResponse.getProducts()).hasAtLeastOneElementOfType(GetProductResponse.class);
        assertThat(productsResponse.getProducts()).hasSize(10);
    }

    @Test
    public void should_find_a_product_by_id() {
        String productId = "C";
        getRequest("/products/" + productId)
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(productId))
                .body("price", equalTo(13.30f))
                .body("quantity", equalTo(0))
                .body("name", equalTo("Steampunk Face"))
                .body("type", equalTo(RobotPartType.FACE.name()))
                .body("productGroup", equalTo(ProductGroupEnum.ROBOT.name()));
    }

}
