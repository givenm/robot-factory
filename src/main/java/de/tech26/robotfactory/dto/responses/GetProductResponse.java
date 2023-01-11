package de.tech26.robotfactory.dto.responses;

import de.tech26.robotfactory.model.Product;

import java.math.BigDecimal;

public class GetProductResponse {
    private String id;
    private BigDecimal price;
    private Long quantity;
    private String name;

    public GetProductResponse(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.name = product.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
