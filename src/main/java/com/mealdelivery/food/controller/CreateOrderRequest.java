package com.mealdelivery.food.controller;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateOrderRequest {

    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String address;

    private boolean needChange;
    private boolean payByCard;

    private double cash;

    @NotNull
    private Integer[] positionId;
}
