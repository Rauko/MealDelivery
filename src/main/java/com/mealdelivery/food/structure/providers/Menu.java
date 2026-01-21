package com.mealdelivery.food.structure.providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "menu")
public class Menu {
    @Id
    private Integer menuId;

    private List<Position> position;
    private List<Set<Position>> mealSet;

}
