package de.tech26.robotfactory.dto.requests;

import de.tech26.robotfactory.enums.ProductGroupEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateOrderRequest {

    @NotNull(message = "Please provide product type for your order")
    private ProductGroupEnum productType;

    @NotNull(message = "Please provide components for your order")
    @Size(min = 1, message = "Your order must have a minimum of 1 component")
    private List<String> components;

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public ProductGroupEnum getProductType() {
        return productType;
    }

    public void setProductType(ProductGroupEnum productType) {
        this.productType = productType;
    }
}
