package com.mealdelivery.food.persistance;

import com.mealdelivery.food.structure.users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
