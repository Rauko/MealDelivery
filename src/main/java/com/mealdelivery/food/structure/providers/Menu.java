package com.mealdelivery.food.structure.providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "menu")
public class Menu {
    @Id
    Integer menuId;
    List<Position> position;
    List<Set<Position>> mealSet;

    public Integer getMenuId() {
        return menuId;
    }

    public List<Position> getPosition() {
        return position;
    }

    public void setPosition(List<Position> position) {
        this.position = position;
    }

    public List<Set<Position>> getMealSet() {
        return mealSet;
    }

    public void setMealSet(Set<Position> set) {
        this.mealSet.add(set);
    }
}
