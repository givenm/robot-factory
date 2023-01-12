package de.tech26.robotfactory.repository;

import de.tech26.robotfactory.db.Database;
import de.tech26.robotfactory.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class OrdersRepository extends Database<String, Order> {
}
