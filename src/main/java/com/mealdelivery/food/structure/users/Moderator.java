package com.mealdelivery.food.structure.users;

public class Moderator extends User {
    ModeratorLevel moderatorLevel;
    public Moderator(Integer id, String name, String email, Integer phone, String hashedPassword) {
        super(id, name, email, phone, hashedPassword);
    }
}
