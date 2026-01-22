package com.mealdelivery.food.structure.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private Long orderId;

    private Instant orderPlacedAt;
    private Instant orderTossedToDelivery;
    private Instant orderDelivered;

    private String addressToDeliver;
    private List<Integer> positionIds;

    private String courier;
    private double price;

    private OrderStatus orderStatus;

    private boolean payByCard;
    private boolean customerNeedChange;
    private double changeFrom;
}
