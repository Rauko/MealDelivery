package com.mealdelivery.food.controller;

import com.mealdelivery.food.service.PositionService;
import com.mealdelivery.food.structure.providers.Position;
import com.mealdelivery.food.structure.providers.Visibility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {

    private PositionService positionService;

    @GetMapping
    public ResponseEntity<List<Position>> getPositions() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }

    @GetMapping("/{positionId}")
    public ResponseEntity<Position> getPosition(@PathVariable Integer positionId) {
        return ResponseEntity.ok(positionService.getPosition(positionId));
    }

    @PostMapping("/add")
    public ResponseEntity<Position> addPosition(@RequestBody CreatePositionRequest request) {
        Position position = positionService.addPosition(
                request.getName(),
                request.getPrice(),
                request.getDescription(),
                request.getWeight(),
                request.getIngredients());
        return ResponseEntity.ok(position);
    }

    @PostMapping("/{positionId}/update")
    public ResponseEntity<Position> updatePosition(@PathVariable Integer positionId,
                                                   @RequestBody UpdatePositionRequest request) {
        if(request.getName() != null) {
            positionService.editPositionName(positionId, request.getName());
        }
        if(request.getPrice() != null) {
            positionService.editPositionPrice(positionId, request.getPrice());
        }
        if(request.getDescription() != null) {
            positionService.editPositionDescription(positionId, request.getDescription());
        }
        if(request.getWeight() != null){
            positionService.editpositionWeight(positionId, request.getWeight());
        }
        if(request.getIngredients() != null){
            positionService.editPositionIngredients(positionId, request.getIngredients());
        }
        if(request.getVisibility() != null){
            positionService.editPositionVisibility(positionId, request.getVisibility());
        }

        return ResponseEntity.ok(positionService.getPosition(positionId));
    }

    @DeleteMapping("/{positionId}/delete")
    public ResponseEntity<Object> deletePosition (@PathVariable Integer positionId) {
        positionService.deletePosition(positionId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{positionId}/edit/visibility")
    public ResponseEntity<Position> editPositionVisibility(@PathVariable Integer positionId,
                                                           @RequestParam Visibility newVisibility) {
        return ResponseEntity.ok(positionService.editPositionVisibility(positionId, newVisibility));
    }
}
