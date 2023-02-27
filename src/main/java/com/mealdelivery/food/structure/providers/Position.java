package com.mealdelivery.food.structure.providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "positions")
public class Position {
    Integer positionId;
    String[] positionName;
    Double positionPrice;
    short weight;
    List<String> ingredients;
}