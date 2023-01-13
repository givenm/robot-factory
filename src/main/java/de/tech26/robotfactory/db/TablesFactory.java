package de.tech26.robotfactory.db;

import de.tech26.robotfactory.model.Order;
import de.tech26.robotfactory.model.Product;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tables are Low level and should not be accessed outside package
 */
class TablesFactory {
    private static final Map<String, Order> ORDERS_TABLE;
    private static final Map<String, Product> PRODUCTS_TABLE;

    static {
        ORDERS_TABLE = new ConcurrentHashMap<>();
        PRODUCTS_TABLE = new ConcurrentHashMap<>();
    }

    public static <T, R> Map<R, T> getTable(@NotNull Class<T> clazz) {
        //Could use strategy pattern here again but leaving it as tables are added by different process in prod.
        if (Order.class == clazz) {
            return (Map<R, T>) ORDERS_TABLE;
        } else if (Product.class == clazz) {
            return (Map<R, T>) PRODUCTS_TABLE;
        }
        throw new RuntimeException("The Table for entity prvided does not exist!");
    }

}
