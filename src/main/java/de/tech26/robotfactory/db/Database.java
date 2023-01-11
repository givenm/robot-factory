package de.tech26.robotfactory.db;

import de.tech26.robotfactory.model.Product;
import de.tech26.robotfactory.model.annotations.Id;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.*;

public abstract class Database<R, T> {

    public void save(@NotNull T entity) {
        assert entity != null;
        Field foundIdField = getIdField(entity);
        foundIdField.setAccessible(true);
        try {
            Object value = foundIdField.get(entity);
            foundIdField.setAccessible(false);
            if(value == null){
                throw new RuntimeException("Id of an entity must not be null");
            }
            Map<R, T> table = (Map<R, T>) TablesFactory.getTable(entity.getClass());
            assert table != null;
            table.put((R) value, entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> Field getIdField(T entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field-> field.isAnnotationPresent(Id.class))
                .findAny()
                .orElseThrow(()-> new RuntimeException("Please specify an id annotation in the model entity"));
    }

    public Optional<T> findById(@NotNull  String id, @NotNull  Class<T> clazz) {
        assert id != null;
        assert clazz != null;
        Map<R, T> table = TablesFactory.getTable(clazz);
        T entity = table.get((T)id);
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