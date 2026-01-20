package com.mealdelivery.food.controller;

import com.mealdelivery.food.structure.providers.Visibility;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CreatePositionRequest {

    @NotBlank
    private String name;

    @NotNull
    private Double price;

    @NotBlank
    private String description;

    @NotNull
    private short weight;

    @NotEmpty
    private List<String> ingredients;

    private Visibility visibility;
}
