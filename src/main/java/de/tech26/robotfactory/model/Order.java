package de.tech26.robotfactory.model;

import de.tech26.robotfactory.annotations.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    @Id
    private String id;
    private List<String> productIds;
    private String customerId;
    private BigDecimal total;
    private Date orderedAt;

    public Order(String id, List<String> productIds, String customerId, BigDecimal total, Date orderedAt) {
        this.id = id;
        this.productIds = productIds;
        this.customerId = customerId;
        this.total = total;
        this.orderedAt = orderedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
    }
}
