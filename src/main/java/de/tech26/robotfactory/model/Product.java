package de.tech26.robotfactory.model;

import de.tech26.robotfactory.enums.ProductGroupEnum;
import de.tech26.robotfactory.model.annotations.Id;

import java.math.BigDecimal;

public class Product {
    @Id
    private String id;
    private BigDecimal price;
    private Long quantity;
    private String name;
    private String type;
    private ProductGroupEnum productGroup;

    public Product(String id, BigDecimal price, Long quantity, String name, String type, ProductGroupEnum productGroup) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.type = type;
        this.productGroup = productGroup;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProductGroupEnum getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupEnum productGroup) {
        this.productGroup = productGroup;
    }
}
