package de.tech26.robotfactory.db;

import de.tech26.robotfactory.annotations.Id;
import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Some form of DB storage. In memory for simulation of data and to demonstrate Java generics only but
 * a real database, externally, is a must so that the app can be scaled horizontally
 **/
public abstract class Database<R, T> {
    public void save(@NotNull T entity) {
        assert entity != null;
        Field foundIdField = getIdField(entity);

        Object value = null;
        try {
            foundIdField.setAccessible(true);
            value = foundIdField.get(entity);
            foundIdField.setAccessible(false);
        } catch (Exception e) {
            throw new GlobalRuntimeException(ErrorCodesEnum.GENERAL_SERVER_ERROR, e.getMessage());
        }

        if (value == null) {
            throw new GlobalRuntimeException(ErrorCodesEnum.GENERAL_SERVER_ERROR, "Value of the entity id marked with @Id must not be null");
        }
        Map<R, T> table = (Map<R, T>) TablesFactory.getTable(entity.getClass());
        assert table != null;
        table.put((R) value, entity);

    }

    private static <T> Field getIdField(T entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findAny()
                .orElseThrow(() -> new GlobalRuntimeException(ErrorCodesEnum.GENERAL_SERVER_ERROR, "Please specify an id annotation in the model entity"));
    }

    public Optional<T> findById(@NotNull String id, @NotNull Class<T> clazz) {
        assert id != null;
        assert clazz != null;
        Map<R, T> table = TablesFactory.getTable(clazz);
        T entity = table.get((T) id);
        if (entity != null)
            return Optional.of(entity);
        else
            return Optional.empty();
    }

    public List<T> findAll(@NotNull Class<T> clazz) {
        assert clazz != null;
        //Can be enhanced with pagination
        Map<R, T> table = TablesFactory.getTable(clazz);
        return new LinkedList<>(table.values());
    }
}