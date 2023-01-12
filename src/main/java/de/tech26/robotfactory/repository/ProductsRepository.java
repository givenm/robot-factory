package de.tech26.robotfactory.repository;

import de.tech26.robotfactory.db.Database;
import de.tech26.robotfactory.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductsRepository extends Database<String, Product> {
}
