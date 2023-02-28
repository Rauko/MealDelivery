package com.mealdelivery.food.persistance;

import com.mealdelivery.food.structure.providers.Position;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PositionRepository extends MongoRepository<Position, Integer> {
}
