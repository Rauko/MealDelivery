package com.mealdelivery.food.structure.userActions;

import com.mealdelivery.food.structure.providers.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModeratorActions {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Set<Position> mealSetBuilder(Set<Integer> positionId) {

        List<Position> positions = mongoTemplate.find(
                new org.springframework.data.mongodb.core.query.Query(
                        org.springframework.data.mongodb.core.query.Criteria.where("positionId").in(positionId)),
                Position.class);

        return new HashSet<>(positions);
    }

    public double getTotalPrice(Set<Position> positions) {
        double totalPrice = positions.stream()
                .mapToDouble(Position::getPositionPrice)
                .sum();

        return totalPrice * 0.9;
    }
}
