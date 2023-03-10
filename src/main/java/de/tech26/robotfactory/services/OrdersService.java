package de.tech26.robotfactory.services;

import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.dto.responses.CreateOrderResponse;
import de.tech26.robotfactory.dto.responses.GetOrderResponse;
import de.tech26.robotfactory.dto.responses.GetOrdersResponse;

import javax.validation.constraints.NotNull;

public interface OrdersService {
    CreateOrderResponse createOrder(@NotNull CreateOrderRequest  createOrderRequest, @NotNull String customerId);

    GetOrderResponse getOrder(String id);

    GetOrdersResponse getAllOrders();
}
