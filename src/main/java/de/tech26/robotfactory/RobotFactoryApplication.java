package de.tech26.robotfactory;

import de.tech26.robotfactory.enums.ProductGroupEnum;
import de.tech26.robotfactory.enums.RobotPartType;
import de.tech26.robotfactory.model.ProductBuilder;
import de.tech26.robotfactory.repository.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class RobotFactoryApplication implements CommandLineRunner {

    private final ProductsRepository productsRepository;

    public RobotFactoryApplication(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RobotFactoryApplication.class, args);
    }


    // Just for loading products into Memory on every run.
    // Prod can use other means like flyway or liquibase
    @Override
    public void run(String args[]) {
        productsRepository.save(new ProductBuilder().withId("A").withPrice(new BigDecimal("10.28")).withQuantity(9L).withName("Humanoid Face").withType(RobotPartType.FACE.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
        productsRepository.save(new ProductBuilder().withId("B").withPrice(new BigDecimal("24.07")).withQuantity(7L).withName("LCD Face").withType(RobotPartType.FACE.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
        productsRepository.save(new ProductBuilder().withId("C").withPrice(new BigDecimal("13.30")).withQuantity(0L).withName("Steampunk Face").withType(RobotPartType.FACE.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
        productsRepository.save(new ProductBuilder().withId("D").withPrice(new BigDecimal("28.94")).withQuantity(1L).withName("Arms with Hands").withType(RobotPartType.ARMS.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
        productsRepository.save(new ProductBuilder().withId("E").withPrice(new BigDecimal("12.39")).withQuantity(3L).withName("Arms with Grippers").withType(RobotPartType.ARMS.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
        productsRepository.save(new ProductBuilder().withId("F").withPrice(new BigDecimal("30.77")).withQuantity(2L).withName("Mobility with Wheels").withType(RobotPartType.MOBILITY.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
        productsRepository.save(new ProductBuilder().withId("G").withPrice(new BigDecimal("55.13")).withQuantity(15L).withName("Mobility with Legs").withType(RobotPartType.MOBILITY.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
        productsRepository.save(new ProductBuilder().withId("H").withPrice(new BigDecimal("50.00")).withQuantity(7L).withName("Mobility with Tracks").withType(RobotPartType.MOBILITY.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
        productsRepository.save(new ProductBuilder().withId("I").withPrice(new BigDecimal("90.12")).withQuantity(92L).withName("Material Bioplastic").withType(RobotPartType.MATERIAL.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
        productsRepository.save(new ProductBuilder().withId("J").withPrice(new BigDecimal("82.31")).withQuantity(15L).withName("Material Metallic").withType(RobotPartType.MATERIAL.name()).withProductGroup(ProductGroupEnum.ROBOT).createProduct());
    }
}
