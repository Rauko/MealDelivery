package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.PositionRepository;
import com.mealdelivery.food.structure.providers.Position;
import com.mealdelivery.food.structure.providers.Visibility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    private Position getPositionOrThrow(Integer positionId) {
        return positionRepository.findById(positionId)
                .orElseThrow(() -> new IllegalArgumentException("Position Not Found: " + positionId));
    }

    public Position getPosition(Integer positionId) {
        return getPositionOrThrow(positionId);
    }

    public Position addPosition(String name,
                                Double price,
                                String description,
                                short weight,
                                List<String> ingredients) {
        Position position = Position.builder()
                .positionName(name)
                .positionPrice(price)
                .positionDescription(description)
                .weight(weight)
                .ingredients(ingredients)
                .visibility(Visibility.HIDDEN)
                .build();

        return positionRepository.insert(position);
    }

    public void deletePosition(Integer positionId) {
        if(!positionRepository.existsById(positionId)) {
            throw new IllegalArgumentException("Position Not Found: " + positionId);
        }

        positionRepository.deleteById(positionId);
    }

    public Position editPositionIngredients(Integer positionId, List<String> newIngredients) {
        Position position = getPositionOrThrow(positionId);
        position.setIngredients(newIngredients);
        return positionRepository.save(position);
    }

    public Position editPositionPrice(Integer positionId, Double price) {
        Position position = getPositionOrThrow(positionId);
        position.setPositionPrice(price);
        return positionRepository.save(position);
    }

    public Position editPositionDescription(Integer positionId, String newDescription) {
        Position position = getPositionOrThrow(positionId);
        position.setPositionDescription(newDescription);
        return positionRepository.save(position);
    }

    public Position editPositionWeight(Integer positionId, short weight) {
        Position position = getPositionOrThrow(positionId);
        position.setWeight(weight);
        return positionRepository.save(position);
    }

    public Position editPositionName(Integer positionId, String newName) {
        Position position = getPositionOrThrow(positionId);
        position.setPositionName(newName);
        return positionRepository.save(position);
    }

    public Position editPositionVisibility(Integer positionId, Visibility newVisibility) {
        Position position = getPositionOrThrow(positionId);
        position.setVisibility(newVisibility);
        return positionRepository.save(position);
    }
}
