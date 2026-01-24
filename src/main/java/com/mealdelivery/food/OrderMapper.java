package com.mealdelivery.food;

import com.mealdelivery.food.dto.OrderDTO;
import com.mealdelivery.food.structure.orders.Order;

public class OrderMapper {

    private OrderMapper() {}

    public static OrderDTO toDTO(Order order) {
        if (order == null) return null;

        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderPlacedAt(order.getOrderPlacedAt())
                .orderTossedToDelivery(order.getOrderTossedToDelivery())
                .orderStatus(order.getOrderStatus())
                .addressToDeliver(order.getAddressToDeliver())
                .positionsIds(order.getPositionIds())
                .courier(order.getCourier())
                .price(order.getPrice())
                .orderStatus(order.getOrderStatus())
                .payByCard(order.isPayByCard())
                .customerNeedChange(order.isCustomerNeedChange())
                .changeFrom(order.getChangeFrom())
                .build();
    }
}
