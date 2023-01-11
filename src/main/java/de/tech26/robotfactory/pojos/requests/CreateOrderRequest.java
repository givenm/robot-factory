package de.tech26.robotfactory.pojos.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateOrderRequest {
    @Size(min = 4, max = 4, message = "Your order must contain one, and only one, part of face, material, arms and mobility")
    private List<String> components;

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }
}
