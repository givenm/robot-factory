package de.tech26.robotfactory.ordersassembly.robot;

import de.tech26.robotfactory.dto.AssembledOrder;
import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.enums.ProductGroupEnum;
import de.tech26.robotfactory.model.Order;
import de.tech26.robotfactory.model.Product;
import de.tech26.robotfactory.ordersassembly.OrderStrategy;
import de.tech26.robotfactory.repository.ProductsRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class RobotOrderStrategy implements OrderStrategy {
    private final ProductsRepository productsRepository;

    public RobotOrderStrategy(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public ProductGroupEnum getStrategyName() {
        return ProductGroupEnum.ROBOT;
    }

    @Override
    public AssembledOrder executeOrder(CreateOrderRequest createOrderRequest, String customerId) {
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
        return new AssembledOrder(order, productsForAssembly);
    }

}
