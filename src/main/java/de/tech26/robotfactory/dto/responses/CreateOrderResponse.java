package de.tech26.robotfactory.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CreateOrderResponse {
    @JsonProperty("order_id")
    private String orderId;
    private BigDecimal total;

    public CreateOrderResponse(String orderId, BigDecimal total) {
        this.orderId = orderId;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
