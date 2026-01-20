package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.PositionRepository;
import com.mealdelivery.food.structure.providers.Position;
import com.mealdelivery.food.structure.providers.Visibility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public void deletePosition(Integer positionId) {
        positionRepository.deleteById(positionId);
    }

    private Position constructPosition (String positionName, Double positionPrice, String description, short weight, List<String> ingredients) {
        return Position.builder()
                .positionName(positionName)
                .positionPrice(positionPrice)
                .positionDescription(description)
                .ingredients(ingredients)
                .weight(weight)
                .visibility(Visibility.HIDDEN)
                .build();
    }

    public Position addPosition(String positionName, Double positionPrice, String description, short weight, List<String> ingredients) {
        return positionRepository.insert(constructPosition(positionName,positionPrice, description,weight,ingredients));
    }

    public Position editPositionIngredients(Integer positionId, List<String> newIngredients) {
        Position currentPosition = positionRepository.findById(positionId).orElse(null);
        currentPosition.setIngredients(newIngredients);
        return positionRepository.save(currentPosition);
    }

    public Position editPositionPrice(Integer positionId, double newPrice) {
        Position currentPosition = positionRepository.findById(positionId).orElse(null);
        currentPosition.setPositionPrice(newPrice);
        return positionRepository.save(currentPosition);
    }

    public Position editPositionName(Integer positionId, String newName) {
        Position currentPosition = positionRepository.findById(positionId).orElse(null);
        currentPosition.setPositionName(newName);
        return positionRepository.save(currentPosition);
    }

    public Position getPosition(Integer positionId) {
        return positionRepository.findById(positionId).orElse(null);
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Position makeMealSet(Integer... positionId) {
        List<Position> setOfPositions = arrayToListPositions(positionId);
        return Position.builder()
                .positionName(new String("New Position!"))
                .positionPrice(setOfPositions.stream()
                        .mapToDouble(Position::getPositionPrice)
                        .sum()*0.9)
                .positionDescription(descriptionCompilation(setOfPositions.stream()
                        .map(Position::getPositionName)
                        .toArray(String[]::new)))
                .ingredients(ingredientCompilation((List<String>) setOfPositions.stream()
                        .map(Position::getIngredients)))
                .weight((short) setOfPositions.stream()
                        .mapToInt(Position::getWeight)
                        .sum())
                .visibility(Visibility.HIDDEN)
                .build();
    }

    private String descriptionCompilation(String... descriptions) {
        String description = new String();
        for(int i = 0; i < descriptions.length; i++){
            description = description + descriptions[i] + ". ";
        }
        return description;
    }

    @SafeVarargs
    private List<String> ingredientCompilation(List<String>... ingrs) {
        Set<String> uniqueIngredients = new HashSet<>();
        for (List<String> ingredientsList : ingrs) {
            uniqueIngredients.addAll(ingredientsList);
        }
        return new ArrayList<>(uniqueIngredients);
    }

    public Position editPositionVisibility(Integer positionId, Visibility newVisibility) {
        Position currentPosition = positionRepository.findById(positionId).orElse(null);
        currentPosition.setVisibility(newVisibility);
        return positionRepository.save(currentPosition);
    }

    private List<Position> arrayToListPositions(Integer... positionId) {
        List<Position> listOfPositions = new ArrayList<>();
        for(int i = 0; i < positionId.length; i++){
            listOfPositions.add(positionRepository.findById(positionId[i]).orElse(null));
        }
        return listOfPositions;
    }

    public void editPositionDescription(Integer positionId, String description) {
        positionRepository.findById(positionId).orElse(null).setPositionDescription(description);
    }

    public void editpositionWeight(Integer positionId, short weight) {
        positionRepository.findById(positionId).orElse(null).setWeight(weight);
    }
}
