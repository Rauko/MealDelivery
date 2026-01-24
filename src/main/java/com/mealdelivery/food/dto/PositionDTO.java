package com.mealdelivery.food.dto;

import com.mealdelivery.food.structure.providers.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {

    private Integer positionId;

    private String positionName;
    private Double positionPrice;

    private short weight;       //works up to 32700gram/32.7kg
    private List<String> ingredients;

    private String positionDescription;
    private Visibility visibility;
}
