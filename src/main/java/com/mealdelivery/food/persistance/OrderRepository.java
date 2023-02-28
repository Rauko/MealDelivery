package com.mealdelivery.food.persistance;

import com.mealdelivery.food.structure.orders.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Integer> {

}
