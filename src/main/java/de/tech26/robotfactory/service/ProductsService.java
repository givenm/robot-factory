package de.tech26.robotfactory.service;

import de.tech26.robotfactory.dto.responses.GetProductResponse;
import de.tech26.robotfactory.dto.responses.GetProductsResponse;

public interface ProductsService {

    public GetProductResponse getProduct(String productId);

    public GetProductsResponse getAllProducts();
}
