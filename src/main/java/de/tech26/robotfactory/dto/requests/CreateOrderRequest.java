package de.tech26.robotfactory.dto.requests;

import de.tech26.robotfactory.enums.ProductGroupEnum;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateOrderRequest {

    @NotNull(message = "Please provide product type for your order")
    private ProductGroupEnum productType;
    @Size(min = 4, max = 4, message = "Your order must have 4 components; containing one, and only one, part of face, material, arms and mobility")
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
