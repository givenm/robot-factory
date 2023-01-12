package de.tech26.robotfactory.service.Impl;

import de.tech26.robotfactory.dto.AssembledOrder;
import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.dto.responses.CreateOrderResponse;
import de.tech26.robotfactory.dto.responses.GetOrderResponse;
import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.enums.ProductGroupEnum;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;
import de.tech26.robotfactory.model.Order;
import de.tech26.robotfactory.model.Product;
import de.tech26.robotfactory.orderassembly.OrderStrategyFactory;
import de.tech26.robotfactory.repository.OrdersRepository;
import de.tech26.robotfactory.repository.ProductsRepository;
import de.tech26.robotfactory.service.OrdersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final ProductsRepository productsRepository;
    private final OrdersRepository ordersRepository;
    private final OrderStrategyFactory orderStrategyFactory;

    public OrdersServiceImpl(ProductsRepository productsRepository, OrdersRepository ordersRepository, OrderStrategyFactory orderStrategyFactory) {
        this.productsRepository = productsRepository;
        this.ordersRepository = ordersRepository;
        this.orderStrategyFactory = orderStrategyFactory;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, String customerId) {
        //With the strategy pattern used and when we add a new product, we don't have to update anything here
        //We just create a new implementation of fulfilling the order by just creating a new concrete class
        //that extends OrderStrategy and writes its implementation like done in RobotOrderStrategy.
        //This will work well if the API will not change much or get blotted with optional variables to accommodate many kinds of products assembly different to robots.
        AssembledOrder assembledOrder = orderStrategyFactory
                .findOrderStrategy(createOrderRequest.getProductType())
                .orElseThrow(() -> new GlobalRuntimeException(ErrorCodesEnum.UNKNOWN_PRODUCT))
                .createOrder(createOrderRequest, customerId);
        saveOrderAndUpdateStock(assembledOrder.getOrder(), assembledOrder.getProductsUsed());
        return new CreateOrderResponse(assembledOrder.getOrder().getId(), assembledOrder.getOrder().getTotal());
    }

    private void saveOrderAndUpdateStock(Order order, List<Product> productsForAssembly) {
        //In proper DB,
        // 1. we can utilize save all at once to reduce DB round trips
        // 2. perform write products with query conditions to make sure that we are updating the quantity correctly and still in a transaction
        productsForAssembly.forEach(product -> {
            product.setQuantity(product.getQuantity() - 1);
            productsRepository.save(product);
        });
        ordersRepository.save(order);
    }

    @Override
    public GetOrderResponse getProduct(String id) {
        return ordersRepository.findById(id, Order.class)
                .map(GetOrderResponse::new)
                .orElseThrow(() -> new GlobalRuntimeException(ErrorCodesEnum.ITEM_NOT_FOUND));
    }
}