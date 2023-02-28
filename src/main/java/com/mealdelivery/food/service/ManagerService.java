package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.PositionRepository;
import com.mealdelivery.food.structure.providers.Position;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ManagerService {
    private final PositionRepository positionRepository;

    @Autowired
    public ManagerService(PositionRepository orderRepository) {
        this.positionRepository = orderRepository;
    }

    public List<Position> getAllTasks() {
        return positionRepository.findAll();
    }

    public Position addTask(String[] positionName, Double positionPrice, short weight, List<String> ingredients) {
        return positionRepository.insert(constructPosition(positionName,positionPrice,weight,ingredients));
    }

    private Position constructPosition (String[] positionName, Double positionPrice, short weight, List<String> ingredients) {
        return Position.builder()
                .positionName(positionName)
                .positionPrice(positionPrice)
                .ingredients(ingredients)
                .weight(weight)
                .build();
    }

    public void deletePosition(Integer positionId) {
        positionRepository.deleteById(positionId);
    }
}
