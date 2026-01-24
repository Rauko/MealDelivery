package com.mealdelivery.food;

import com.mealdelivery.food.dto.PositionDTO;
import com.mealdelivery.food.structure.providers.Position;

public class PositionMapper {

    private PositionMapper() {}

    public static PositionDTO toDTO(Position position){
        if(position == null) return null;

        return PositionDTO.builder()
                .positionId(position.getPositionId())
                .positionName(position.getPositionName())
                .positionPrice(position.getPositionPrice())
                .weight(position.getWeight())
                .ingredients(position.getIngredients())
                .positionDescription(position.getPositionDescription())
                .visibility(position.getVisibility())
                .build();

    }
}
