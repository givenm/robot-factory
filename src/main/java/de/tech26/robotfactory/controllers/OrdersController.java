package de.tech26.robotfactory.controllers;
import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.dto.responses.CreateOrderResponse;
import de.tech26.robotfactory.service.OrdersService;
import de.tech26.robotfactory.utils.RestUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid  @RequestBody CreateOrderRequest createOrderRequest){
        return RestUtil.toResponseEntity(ordersService.createOrder(createOrderRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResponse> getOrderById(@Valid  @RequestBody CreateOrderRequest createOrderRequest){
        return RestUtil.toResponseEntity(ordersService.createOrder(createOrderRequest), HttpStatus.CREATED);
    }
}
