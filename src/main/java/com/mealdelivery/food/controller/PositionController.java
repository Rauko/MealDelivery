package com.mealdelivery.food.controller;

import com.mealdelivery.food.service.PositionService;
import com.mealdelivery.food.structure.providers.Position;
import com.mealdelivery.food.structure.providers.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PositionController {
    private PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/positions/all")
    public ResponseEntity<List<Position>> getAllTasks() {
        List<Position> positions = positionService.getAllPositions();
        return ResponseEntity.ok().body(positions);
    }

    @GetMapping("/positions/{id}")
    public ResponseEntity<Position> getPosition(Integer positionId) {
        Position task = positionService.getPosition(positionId);;
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/positions/add")
    public ResponseEntity<Position> addTask(@RequestParam String[] positionName, Double positionPrice,
                                            String[] description, short weight, List<String> ingredients) {
        Position positions = positionService.addPosition(positionName, positionPrice, description, weight, ingredients);
        return ResponseEntity.ok().body(positions);
    }

    @DeleteMapping("/positions/delete/{id}")
    public ResponseEntity<Object> deletePosition (@PathVariable Integer positionId) {
        positionService.deletePosition(positionId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/positions/edit/{id}/editpositioningredients/{newIngredients}")
    public ResponseEntity<Position> editPositionIngredients(@PathVariable Integer positionId, @PathVariable List<String> newIngredients) {
        Position task = positionService.editPositionIngredients(positionId, newIngredients);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/positions/edit/{id}/editvisibility/{newVisibility}")
    public ResponseEntity<Position> editPositionVisibility(@PathVariable Integer positionId, @PathVariable Visibility newVisibility) {
        Position task = positionService.editPositionVisibility(newVisibility, positionId);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/positions/edit/{id}/editpositionname/{newPositionName}")
    public ResponseEntity<Position> setPositionName(@PathVariable Integer positionId, @PathVariable String[] newPositionName) {
        Position task = positionService.editPositionName(positionId, newPositionName);
        return ResponseEntity.ok().body(task);
    }
}
