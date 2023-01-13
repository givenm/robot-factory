package de.tech26.robotfactory.services;

import de.tech26.robotfactory.dto.AssembledOrder;
import de.tech26.robotfactory.dto.requests.CreateOrderRequest;
import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.enums.ProductGroupEnum;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;
import de.tech26.robotfactory.model.Order;
import de.tech26.robotfactory.model.OrderBuilder;
import de.tech26.robotfactory.model.Product;
import de.tech26.robotfactory.model.ProductBuilder;
import de.tech26.robotfactory.ordersassembly.OrderStrategy;
import de.tech26.robotfactory.ordersassembly.OrderStrategyFactory;
import de.tech26.robotfactory.repository.OrdersRepository;
import de.tech26.robotfactory.repository.ProductsRepository;
import de.tech26.robotfactory.services.Impl.OrdersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrdersServiceImplTest {

    private OrdersService ordersService;
    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private OrdersRepository ordersRepository;
    @Mock
    private OrderStrategy orderStrategy;
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
    void should_decrease_quantity_when_order_is_created() {
        when(orderStrategyFactory.findOrderStrategy(any())).thenReturn(Optional.of(orderStrategy));
        Order order = new OrderBuilder()
                .withCustomerId("123-customer-id")
                .createOrder();
        Product product = new ProductBuilder()
                .withQuantity(2L)
                .createProduct();
        AssembledOrder assembledOrder = new AssembledOrder(order, Collections.singletonList(product));
        when(orderStrategy.executeOrder(any(), anyString())).thenReturn(assembledOrder);

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setComponents(Arrays.asList("I", "A", "D", "F"));
        createOrderRequest.setProductType(ProductGroupEnum.ROBOT);
        ordersService.createOrder(createOrderRequest, "123-customer-id");

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productsRepository).save(productArgumentCaptor.capture());
        //Quantity should have been decreased by 1
        assertThat(productArgumentCaptor.getValue().getQuantity()).isEqualTo(1L);
        verify(ordersRepository).save(isNotNull());
    }

    @Test
    void should_throw_unknown_product_when_product_strategy_not_found() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setComponents(Arrays.asList("I", "A", "D", "F"));
        createOrderRequest.setProductType(null);
        when(orderStrategyFactory.findOrderStrategy(isNull())).thenReturn(Optional.empty());
        GlobalRuntimeException exception = assertThrows(GlobalRuntimeException.class, () -> ordersService.createOrder(createOrderRequest, "customer_id-12345"));
        assertThat(exception.getErrEnum()).isEqualTo(ErrorCodesEnum.UNKNOWN_PRODUCT);
    }

    @Test
    void getAllOrders() {
    }
}