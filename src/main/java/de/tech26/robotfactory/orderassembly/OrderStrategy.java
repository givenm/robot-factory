package de.tech26.robotfactory.orderassembly;

import de.tech26.robotfactory.dto.AssembledOrder;
import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.enums.ProductGroupEnum;

public interface OrderStrategy {
    AssembledOrder createOrder(CreateOrderRequest createOrderRequest, String customerId);
    ProductGroupEnum getStrategyName();
}
