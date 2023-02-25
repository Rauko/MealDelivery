package com.mealdelivery.food.structure.users;

public class Administrator extends User {
    public Administrator(Integer id, String name, String email, Integer phone, String hashedPassword) {
        super(id, name, email, phone, hashedPassword);
    }
}
