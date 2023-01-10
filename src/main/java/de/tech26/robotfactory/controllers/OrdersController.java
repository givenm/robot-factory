package de.tech26.robotfactory.controllers;

import de.tech26.robotfactory.pojos.responses.CreateOrderResponse;
import de.tech26.robotfactory.utils.RestUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(){
        return RestUtil.toResponseEntity(new CreateOrderResponse(), HttpStatus.CREATED);
    }
}
