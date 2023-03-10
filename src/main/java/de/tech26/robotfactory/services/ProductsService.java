package de.tech26.robotfactory.services;

import de.tech26.robotfactory.dto.responses.GetProductResponse;
import de.tech26.robotfactory.dto.responses.GetProductsResponse;

public interface ProductsService {

    GetProductResponse getProduct(String productId);

    GetProductsResponse getAllProducts();
}
