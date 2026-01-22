package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.PositionRepository;
import com.mealdelivery.food.structure.providers.Position;
import com.mealdelivery.food.structure.providers.Visibility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final PositionRepository positionRepository;

    public void hidePosition(Integer positionId){
        Position p =  positionRepository.findById(positionId)
                .orElseThrow(()-> new IllegalArgumentException("Position Not Found"));
        p.setVisibility(Visibility.HIDDEN);
        positionRepository.save(p);
    }

    public void showPosition(Integer positionId){
        Position p =  positionRepository.findById(positionId)
                .orElseThrow(()-> new IllegalArgumentException("Position Not Found"));
        p.setVisibility(Visibility.VISIBLE);
        positionRepository.save(p);
    }
}
