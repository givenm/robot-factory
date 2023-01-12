package de.tech26.robotfactory.model;

import de.tech26.robotfactory.enums.ProductGroupEnum;

import java.math.BigDecimal;

public class ProductBuilder {
    private String id;
    private BigDecimal price;
    private Long quantity;
    private String name;
    private String type;
    private ProductGroupEnum productGroup;

    public ProductBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductBuilder withQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ProductBuilder withProductGroup(ProductGroupEnum productGroup) {
        this.productGroup = productGroup;
        return this;
    }

    public Product createProduct() {
        return new Product(id, price, quantity, name, type, productGroup);
    }
}