package com.mealdelivery.food.structure.providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "positions")
public class Position {

    @Id
    private Integer positionId;

    private String positionName;
    private Double positionPrice;

    private short weight; //works up to 32700gram/32.7kg

    private List<String> ingredients;
    private String positionDescription;

    private Visibility visibility;
}