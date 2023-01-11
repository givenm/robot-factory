package de.tech26.robotfactory;

import de.tech26.robotfactory.model.Product;
import de.tech26.robotfactory.repository.OrdersRepository;
import de.tech26.robotfactory.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class RobotFactoryApplication implements CommandLineRunner {

    @Autowired
    private ProductsRepository productsRepository;

    public static void main(String[] args) {
        SpringApplication.run(RobotFactoryApplication.class, args);
    }


    // Just for loading products into Memory on every run.
    // Prod can use other means like flyway or liquibase
    @Override
    public void run(String args[]) {
        productsRepository.saveProduct(new Product("A", new BigDecimal("10.28"), 9L, "Humanoid Face"));
        productsRepository.saveProduct(new Product("B", new BigDecimal("24.07"), 7L, "LCD Face"));
        productsRepository.saveProduct(new Product("C", new BigDecimal("13.30"), 0L, "Steampunk Face"));
        productsRepository.saveProduct(new Product("D", new BigDecimal("28.94"), 1L, "Arms with Hands"));
        productsRepository.saveProduct(new Product("E", new BigDecimal("12.39"), 3L, "Arms with Grippers"));
        productsRepository.saveProduct(new Product("F", new BigDecimal("30.77"), 2L, "Mobility with Wheels"));
        productsRepository.saveProduct(new Product("G", new BigDecimal("55.13"), 15L, "Mobility with Legs"));
        productsRepository.saveProduct(new Product("H", new BigDecimal("50.00"), 7L, "Mobility with Tracks"));
        productsRepository.saveProduct(new Product("I", new BigDecimal("90.12"), 92L, "Material Bioplastic"));
        productsRepository.saveProduct(new Product("J", new BigDecimal("82.31"), 15L, "Material Metallic"));
    }
}
