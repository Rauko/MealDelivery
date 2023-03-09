package com.mealdelivery.food.dto;

import com.mealdelivery.food.structure.orders.OrderStatus;
import com.mealdelivery.food.structure.users.Runner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String addressToDeliver;
    private List<Integer> positionsIds;
    private LocalDateTime creationDate;
    private Runner courier;
    private OrderStatus orderStatus;
    private double orderPrice;
    private boolean customerNeedChange;
    private double changeFrom;
}
