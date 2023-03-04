package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    private final PositionRepository positionRepository;

    @Autowired
    public ManagerService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
}
