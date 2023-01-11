package de.tech26.robotfactory.service;

import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.dto.responses.CreateOrderResponse;

public interface OrdersService {
    public CreateOrderResponse createOrder(CreateOrderRequest  createOrderRequest);
}
