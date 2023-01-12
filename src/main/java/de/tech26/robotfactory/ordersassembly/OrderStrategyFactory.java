package de.tech26.robotfactory.ordersassembly;

import de.tech26.robotfactory.enums.ProductGroupEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class OrderStrategyFactory {

    private final Map<ProductGroupEnum, OrderStrategy> orderStrategies;

    public OrderStrategyFactory(Set<OrderStrategy> strategySet) {
        orderStrategies = new HashMap<>();
        strategySet.forEach(strategy -> orderStrategies.put(strategy.getStrategyName(), strategy));
    }

    public Optional<OrderStrategy> findOrderStrategy(ProductGroupEnum strategyName) {
        return Optional.ofNullable(orderStrategies.get(strategyName));
    }
}
