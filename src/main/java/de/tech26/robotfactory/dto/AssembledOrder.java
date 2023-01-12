package de.tech26.robotfactory.dto;

import de.tech26.robotfactory.model.Order;
import de.tech26.robotfactory.model.Product;

import java.util.List;

public class AssembledOrder {
    private Order order;
    List<Product> productsUsed;

    public AssembledOrder(Order order, List<Product> productsUsed) {
        this.order = order;
        this.productsUsed = productsUsed;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Product> getProductsUsed() {
        return productsUsed;
    }

    public void setProductsUsed(List<Product> productsUsed) {
        this.productsUsed = productsUsed;
    }
}
