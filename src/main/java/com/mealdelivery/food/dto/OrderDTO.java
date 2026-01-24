package com.mealdelivery.food.dto;

import com.mealdelivery.food.structure.orders.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;

    private Instant orderPlacedAt;
    private Instant orderTossedToDelivery;
    private Instant orderDelivered;

    private String addressToDeliver;
    private List<Integer> positionsIds;

    private String courier;

    private double price;
    private OrderStatus orderStatus;

    private boolean payByCard;
    private boolean customerNeedChange;
    private double changeFrom;
}
