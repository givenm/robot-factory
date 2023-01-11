package de.tech26.robotfactory.service.Impl;

import de.tech26.robotfactory.dto.responses.GetProductResponse;
import de.tech26.robotfactory.dto.responses.GetProductsResponse;
import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;
import de.tech26.robotfactory.model.Product;
import de.tech26.robotfactory.repository.ProductsRepository;
import de.tech26.robotfactory.service.ProductsService;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public GetProductResponse getProduct(String productId) {
        return productsRepository
                .findById(productId, Product.class)
                .map(GetProductResponse::new)
                .orElseThrow(()-> new GlobalRuntimeException(ErrorCodesEnum.ITEM_NOT_FOUND));
    }

    @Override
    public GetProductsResponse getAllProducts() {
        List<GetProductResponse> products = productsRepository
                .findAll(Product.class)
                .stream()
                .map(GetProductResponse::new)
                .collect(Collectors.toCollection(LinkedList::new));
        return new GetProductsResponse(products);
    }
}
