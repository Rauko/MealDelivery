package com.mealdelivery.food.controller;

import com.mealdelivery.food.structure.providers.Visibility;
import lombok.Data;

import java.util.List;

@Data
public class UpdatePositionRequest {
    private String name;
    private Double price;
    private String description;
    private Short weight;
    private List<String> ingredients;
    private Visibility visibility;
}
