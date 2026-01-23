package com.mealdelivery.food.controller;

import com.mealdelivery.food.structure.delivery.Address;
import com.mealdelivery.food.structure.users.UserStatus;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
    private Long phone;
    private Address address;
    private String password;
    private UserStatus status;
}
