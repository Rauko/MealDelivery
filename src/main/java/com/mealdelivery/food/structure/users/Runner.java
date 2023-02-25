package com.mealdelivery.food.structure.users;

import com.mealdelivery.food.structure.orders.Order;

import java.util.List;

public class Runner extends User  {
    List<Order> ordersToDeliver;

    public Runner(Integer id, String name, String email, Integer phone, String hashedPassword) {
        super(id, name, email, phone, hashedPassword);
    }
}
