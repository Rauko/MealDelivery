package com.mealdelivery.food.structure.providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "positions")
public class Position {

    @Id
    Integer positionId;
    String[] positionName;
    Double positionPrice;
    short weight; //works up to 32700gram/32.7kg
    List<String> ingredients;
    String[] positionDescription;
    Visibility visibility;
}