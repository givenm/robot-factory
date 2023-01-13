package de.tech26.robotfactory.db;

import de.tech26.robotfactory.db.components.DummyModel;
import de.tech26.robotfactory.db.components.DummyRepository;
import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;
import de.tech26.robotfactory.model.Product;
import de.tech26.robotfactory.model.ProductBuilder;
import de.tech26.robotfactory.repository.ProductsRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatabaseTests {

    private final DummyRepository dummyRepository;
    private final ProductsRepository productsRepository;

    public DatabaseTests() {
        this.dummyRepository = new DummyRepository();
        this.productsRepository = new ProductsRepository();
    }

    @Test
    public void throw_error_when_entity_does_not_have_id_annotation() {
        GlobalRuntimeException exception = assertThrows(GlobalRuntimeException.class, () -> {
            DummyModel entityWithNoIdAnnotation = new DummyModel();
            dummyRepository.save(entityWithNoIdAnnotation);
        });
        assertThat(exception.getErrEnum()).isEqualTo(ErrorCodesEnum.GENERAL_SERVER_ERROR);
        assertThat(exception.getResponseMessage()).isEqualTo("Please specify an id annotation in the model entity");
    }

    @Test
    public void throw_error_when_entity_id_has_no_value() {
        GlobalRuntimeException exception = assertThrows(GlobalRuntimeException.class, () -> {
            Product entityWithIdNull = new ProductBuilder()
                    .withId(null)
                    .createProduct();
            productsRepository.save(entityWithIdNull);
        });
        assertThat(exception.getErrEnum()).isEqualTo(ErrorCodesEnum.GENERAL_SERVER_ERROR);
        assertThat(exception.getResponseMessage()).isEqualTo("Value of the entity id marked with @Id must not be null");
    }

    @Test
    public void return_empty_optional_when_item_not_found_by_id() {
        Optional<Product> optionalProduct = productsRepository.findById("id_of_product_that's_not_there", Product.class);
        assertThat(optionalProduct).isEqualTo(Optional.empty());
    }
}
