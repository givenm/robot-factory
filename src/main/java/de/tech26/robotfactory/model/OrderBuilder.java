package de.tech26.robotfactory.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderBuilder {
    private String id;
    private List<String> productIds;
    private String customerId;
    private BigDecimal total;
    private Date orderedAt;

    public OrderBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withProductIds(List<String> productIds) {
        this.productIds = productIds;
        return this;
    }

    public OrderBuilder withCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderBuilder withTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public OrderBuilder withOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
        return this;
    }

    public Order createOrder() {
        return new Order(id, productIds, customerId, total, orderedAt);
    }
}