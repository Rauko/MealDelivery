package com.mealdelivery.food.controller;

import com.mealdelivery.food.structure.delivery.Address;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Long phone;

    @NotBlank
    private Address address;

    @NotBlank
    private String password;
}
