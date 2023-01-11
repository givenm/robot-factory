package de.tech26.robotfactory.service.Impl;

import de.tech26.robotfactory.pojos.requests.CreateOrderRequest;
import de.tech26.robotfactory.pojos.responses.CreateOrderResponse;
import de.tech26.robotfactory.service.OrdersService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        return new CreateOrderResponse(UUID.randomUUID().toString(), new BigDecimal("160.11"));
    }
}
