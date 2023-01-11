package de.tech26.robotfactory.controllers;
import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.dto.responses.CreateOrderResponse;
import de.tech26.robotfactory.dto.responses.GetProductResponse;
import de.tech26.robotfactory.dto.responses.GetProductsResponse;
import de.tech26.robotfactory.service.ProductsService;
import de.tech26.robotfactory.utils.RestUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getProductById(@Valid  @NotEmpty @PathVariable String id){
        return RestUtil.toResponseEntity(productsService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetProductsResponse> getProductProducts(){
        //Could be added:
        //1. Can be paginated for better performance.
        //2. Filtering the products by product type can help client with paginating on specific product grouping for better product listing selection
        return RestUtil.toResponseEntity(productsService.getAllProducts(), HttpStatus.OK);
    }
}
