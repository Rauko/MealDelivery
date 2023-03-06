package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.OrderRepository;
import com.mealdelivery.food.persistance.PositionRepository;
import com.mealdelivery.food.structure.orders.Order;
import com.mealdelivery.food.structure.orders.OrderStatus;
import com.mealdelivery.food.structure.providers.Position;
import com.mealdelivery.food.structure.users.Runner;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PositionRepository positionRepository;

    public OrderService(OrderRepository orderRepository, PositionRepository positionRepository) {
        this.orderRepository = orderRepository;
        this.positionRepository = positionRepository;
    }

    public Order createOrder(String address, boolean needChange, double cash, Integer... positionId) {
        List<Position> listOfPositions = new ArrayList<>();
        for(int i = 0; i < positionId.length; i++){
            listOfPositions.add(positionRepository.findById(positionId[i]).orElse(null));
        }
        return Order.builder()
                .orderPlacedAt(Timestamp.valueOf(LocalDateTime.now()))
                .addressToDeliver(address)
                .positions(listOfPositions)
                .price(listOfPositions.stream()
                        .mapToDouble(Position::getPositionPrice)
                        .sum())
                .orderStatus(OrderStatus.CREATED)
                .customerNeedChange(needChange)
                .changeFrom(cash-listOfPositions.stream()
                        .mapToDouble(Position::getPositionPrice)
                        .sum())
                .build();
    }

    public void deleteOrder(Integer orderId) { //cancel order
        orderRepository.deleteById(orderId);
    }

    public Order setOrderTossedToDeliveryTimestamp(Integer orderId) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        currentOrder.setOrderTossedToDelivery(Timestamp.valueOf(LocalDateTime.now()));
        return orderRepository.save(currentOrder);
    }

    public Order setOrderDeliveredTimestamp(Integer orderId) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        currentOrder.setOrderTossedToDelivery(Timestamp.valueOf(LocalDateTime.now()));
        return orderRepository.save(currentOrder);
    }

    public Order changeAddressToDeliver(Integer orderId, String newAddress) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        currentOrder.setAddressToDeliver(newAddress);
        return orderRepository.save(currentOrder);
    }

    public Order setCourier (Integer orderId, Runner courier) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        currentOrder.setCourier(courier);
        return orderRepository.save(currentOrder);
    }

    public Order changeOrderStatus(Integer orderId, OrderStatus newStatus) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        currentOrder.setOrderStatus(newStatus);
        return orderRepository.save(currentOrder);
    }

    public Order customerNeedChange(Integer orderId, boolean needChange) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        currentOrder.setCustomerNeedChange(needChange);
        return orderRepository.save(currentOrder);
    }
}
