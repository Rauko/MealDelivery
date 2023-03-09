package com.mealdelivery.food.structure.orders;

import com.mealdelivery.food.structure.users.Runner;
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
    Integer orderId;
    Timestamp orderPlacedAt;
    Timestamp orderTossedToDelivery;
    Timestamp orderDelivered;
    String addressToDeliver;
    List<Integer> positionIds;
    Runner courier;
    double price;
    OrderStatus orderStatus;
    boolean customerNeedChange;
    double changeFrom;
}
