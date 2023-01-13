package de.tech26.robotfactory.db;

import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TablesFactoryTests {

    @Test
    public void throw_error_when_table_not_found(){
        GlobalRuntimeException exception = assertThrows(GlobalRuntimeException.class, () -> {
            Map<String, Test> table = TablesFactory.getTable(Test.class);
        });
        assertThat(exception.getErrEnum()).isEqualTo(ErrorCodesEnum.GENERAL_SERVER_ERROR);
        assertThat(exception.getResponseMessage()).isEqualTo("The Table for entity provided does not exist!");
    }
}
