package de.tech26.robotfactory.service.Impl;

import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.dto.responses.CreateOrderResponse;
import de.tech26.robotfactory.dto.responses.GetOrderResponse;
import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.enums.ProductGroupEnum;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;
import de.tech26.robotfactory.model.Order;
import de.tech26.robotfactory.model.Product;
import de.tech26.robotfactory.productassembly.RobotAssembly;
import de.tech26.robotfactory.repository.OrdersRepository;
import de.tech26.robotfactory.repository.ProductsRepository;
import de.tech26.robotfactory.service.OrdersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final ProductsRepository productsRepository;
    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(ProductsRepository productsRepository, OrdersRepository ordersRepository) {
        this.productsRepository = productsRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, String customerId) {

        //Makes this
        if (createOrderRequest.getProductType() == ProductGroupEnum.ROBOT) {
            //1. Query with specific product Ids against external DB is a must. findAll for demonstration only
            //2. We can utilize transactions with select for update to lock the products when reading to make sure that stock
            // is represented accurately but create order process should have a very short timeout.
            List<Product> productsForAssembly = productsRepository
                    .findAll(Product.class)
                    .stream()
                    .filter(product -> product.getProductGroup() == ProductGroupEnum.ROBOT && createOrderRequest.getComponents().contains(product.getId()))
                    .collect(Collectors.toList());

            Order order = new RobotAssembly.Builder(customerId)
                    .withComponents(productsForAssembly)
                    .build();
            //Note: Arms come in pairs on robot orders. So quantity 1, comes with left and right.
            saveOrderAndUpdateStock(order, productsForAssembly);
            //Asynchronously send email to customer for the successful order. Could be delegated to emails microservice
            return new CreateOrderResponse(order.getId(), order.getTotal());
        }
        throw new GlobalRuntimeException(ErrorCodesEnum.UNKNOWN_PRODUCT);
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