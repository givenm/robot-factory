package de.tech26.robotfactory.dto.responses;

import java.util.List;

public class GetOrdersResponse {
    private List<GetOrderResponse> orders;

    public GetOrdersResponse(List<GetOrderResponse> orders) {
        this.orders = orders;
    }

    public GetOrdersResponse() {
    }

    public List<GetOrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<GetOrderResponse> orders) {
        this.orders = orders;
    }
}
