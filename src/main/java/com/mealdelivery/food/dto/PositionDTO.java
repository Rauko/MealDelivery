package com.mealdelivery.food.dto;

import com.mealdelivery.food.structure.providers.Visibility;

import java.util.List;

public class PositionDTO {
    private String[] positionName;
    private Double positionPrice;
    private short weight;       //works up to 32700gram/32.7kg
    private List<String> ingredients;
    private String[] positionDescription;
    private Visibility visibility;
}
