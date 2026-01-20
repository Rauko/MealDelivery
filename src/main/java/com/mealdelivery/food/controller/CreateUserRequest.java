package com.mealdelivery.food.controller;

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
    private String address;

    @NotBlank
    private String password;
}
