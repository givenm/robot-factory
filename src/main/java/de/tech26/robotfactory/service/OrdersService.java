package de.tech26.robotfactory.service;

import de.tech26.robotfactory.pojos.requests.CreateOrderRequest;
import de.tech26.robotfactory.pojos.responses.CreateOrderResponse;

public interface OrdersService {
    public CreateOrderResponse createOrder(CreateOrderRequest  createOrderRequest);
}
