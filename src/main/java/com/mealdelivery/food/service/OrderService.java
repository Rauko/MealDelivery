package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.OrderRepository;
import com.mealdelivery.food.persistance.PositionRepository;
import com.mealdelivery.food.persistance.UserRepository;
import com.mealdelivery.food.structure.orders.Order;
import com.mealdelivery.food.structure.orders.OrderStatus;
import com.mealdelivery.food.structure.providers.Position;
import com.mealdelivery.food.structure.users.RunnerStatus;
import com.mealdelivery.food.structure.users.User;
import com.mealdelivery.food.structure.users.UserStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, PositionRepository positionRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.positionRepository = positionRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders() {
       return orderRepository.findAll();
    }

    public Order getOrder(Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order createOrder(String address, boolean needChange, double cash, Integer... positionId) {
        List<Position> listOfPositions = new ArrayList<>();
        List<Integer> listOfIds = new ArrayList<>();
        for(int i = 0; i < positionId.length; i++){
            listOfPositions.add(positionRepository.findById(positionId[i]).orElse(null));
            listOfIds.add(positionId[i]);
        }
        return Order.builder()
                .orderPlacedAt(Timestamp.valueOf(LocalDateTime.now()))
                .addressToDeliver(address)
                .positionIds(listOfIds)
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

    public Order setCourier (Integer orderId) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        assert currentOrder != null;
        List<User> allAvailableRunners = userRepository.findAll()
                .stream()
                .filter(user -> user.getUserStatus() == UserStatus.COURIER)
                .filter(user -> user.getRunnerStatus() == RunnerStatus.AVAILABLE)
                .toList();
        if (!allAvailableRunners.isEmpty()) {
            int randomIndex = new Random().nextInt(allAvailableRunners.size());
            User randomRunner = allAvailableRunners.get(randomIndex);
            currentOrder.setCourier(randomRunner.getId());
        } else {
            // here will be realisation of phone call from operator one day...
        }
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
