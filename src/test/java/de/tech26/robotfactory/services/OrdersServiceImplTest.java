package de.tech26.robotfactory.services;

import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;
import de.tech26.robotfactory.ordersassembly.OrderStrategyFactory;
import de.tech26.robotfactory.repository.OrdersRepository;
import de.tech26.robotfactory.repository.ProductsRepository;
import de.tech26.robotfactory.services.Impl.OrdersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

class OrdersServiceImplTest {

    private OrdersService ordersService;
    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private OrdersRepository ordersRepository;
    @Mock
    private OrderStrategyFactory orderStrategyFactory;

    public OrdersServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        ordersService = new OrdersServiceImpl(productsRepository, ordersRepository, orderStrategyFactory);
    }

    @Test
    void should_create_robot_order() {
        when(orderStrategyFactory.findOrderStrategy(any())).thenReturn(Optional.empty());

    }

    @Test
    void should_throw_unknown_product_when_product_strategy_not_found() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setComponents(Arrays.asList("I","A","D","F"));
        createOrderRequest.setProductType(null);
        when(orderStrategyFactory.findOrderStrategy(isNull())).thenReturn(Optional.empty());
        GlobalRuntimeException exception = assertThrows(GlobalRuntimeException.class, () -> ordersService.createOrder(createOrderRequest, "customer_id-12345"));
        assertThat(exception.getErrEnum()).isEqualTo(ErrorCodesEnum.UNKNOWN_PRODUCT);
    }

    @Test
    void getAllOrders() {
    }
}