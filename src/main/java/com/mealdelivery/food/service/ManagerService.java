package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.PositionRepository;
import com.mealdelivery.food.structure.providers.Position;
import com.mealdelivery.food.structure.providers.Visibility;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManagerService {
    private final PositionRepository positionRepository;

    @Autowired
    public ManagerService(PositionRepository orderRepository) {
        this.positionRepository = orderRepository;
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Position addPosition(String[] positionName, Double positionPrice, String[] description, short weight, List<String> ingredients) {
        return positionRepository.insert(constructPosition(positionName,positionPrice, description,weight,ingredients));
    }

    private Position constructPosition (String[] positionName, Double positionPrice, String[] description, short weight, List<String> ingredients) {
        return Position.builder()
                .positionName(positionName)
                .positionPrice(positionPrice)
                .positionDescription(description)
                .ingredients(ingredients)
                .weight(weight)
                .build();
    }

    public void deletePosition(Integer positionId) {
        positionRepository.deleteById(positionId);
    }

    public Position editPositionIngredients(List<String> newIngredients, Integer positionId) {
        Position currentPosition = positionRepository.findById(positionId).orElse(null);
        currentPosition.setIngredients(newIngredients);
        return positionRepository.save(currentPosition);
    }

    public Position editVisibility(Visibility newVisibility, Integer positionId) {
        Position currentPosition = positionRepository.findById(positionId).orElse(null);
        currentPosition.setVisibility(newVisibility);
        return positionRepository.save(currentPosition);
    }

    public Position editPositionPrice(double newPrice, Integer positionId) {
        Position currentPosition = positionRepository.findById(positionId).orElse(null);
        currentPosition.setPositionPrice(newPrice);
        return positionRepository.save(currentPosition);
    }

    public Position setPositionName(String[] newName, Integer positionId) {
        Position currentPosition = positionRepository.findById(positionId).orElse(null);
        currentPosition.setPositionName(newName);
        return positionRepository.save(currentPosition);
    }

    public Position makeMealSet(Integer... positionId) {
        List<Position> setOfPositions = new ArrayList<>();
        for(int i = 0; i < positionId.length; i++){
            setOfPositions.add(positionRepository.findById(positionId[i]).orElse(null));
        }
        return Position.builder()
                .positionName(new String[]{"New Position!"})
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

    private String[] descriptionCompilation(String... descriptions) {
        List<String[]> description = new ArrayList<>();
        for(int i = 0; i < descriptions.length; i++){
            description.add(descriptions);
        }
        return (String[]) description.toArray();
    }

    @SafeVarargs
    private List<String> ingredientCompilation(List<String>... ingrs) {
        Set<String> uniqueIngredients = new HashSet<>();
        for (List<String> ingredientsList : ingrs) {
            uniqueIngredients.addAll(ingredientsList);
        }
        return new ArrayList<>(uniqueIngredients);
    }
}
