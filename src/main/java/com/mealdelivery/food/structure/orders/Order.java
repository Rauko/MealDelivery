package com.mealdelivery.food.structure.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    Long orderId;
    Timestamp orderPlacedAt;
    Timestamp orderTossedToDelivery;
    Timestamp orderDelivered;
    String addressToDeliver;
    List<Integer> positionIds;
    String courier;
    double price;
    OrderStatus orderStatus;
    boolean customerNeedChange;
    double changeFrom;
}
