package de.tech26.robotfactory.controllers;

import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.dto.responses.CreateOrderResponse;
import de.tech26.robotfactory.dto.responses.GetOrderResponse;
import de.tech26.robotfactory.dto.responses.GetOrdersResponse;
import de.tech26.robotfactory.service.OrdersService;
import de.tech26.robotfactory.utils.RestUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@RestController
@RequestMapping(path = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        // We get authenticated customer from Principal object as it's passed down.
        // Since we don't have spring security, we will generate a new one instead, for demonstration
        String customerId = UUID.randomUUID().toString();
        return RestUtil.toResponseEntity(ordersService.createOrder(createOrderRequest, customerId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getOrderById(@Valid @NotEmpty @PathVariable String id) {
        return RestUtil.toResponseEntity(ordersService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetOrdersResponse> getProductProducts(){
        //Could be added:
        //1. Pagination for better performance.
        //2. Filters
        return RestUtil.toResponseEntity(ordersService.getAllOrders(), HttpStatus.OK);
    }

    //We could add cancel and update order
}
