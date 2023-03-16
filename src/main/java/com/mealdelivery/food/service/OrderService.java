package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.OrderRepository;
import com.mealdelivery.food.persistance.PositionRepository;
import com.mealdelivery.food.persistance.UserRepository;
import com.mealdelivery.food.structure.orders.Order;
import com.mealdelivery.food.structure.orders.OrderStatus;
import com.mealdelivery.food.structure.providers.Position;
import com.mealdelivery.food.structure.users.EmployeeState;
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

    public OrderService(OrderRepository orderRepository, PositionRepository positionRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.positionRepository = positionRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders() {
       return orderRepository.findAll();
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order setOrder(String address, boolean needChange, double cash, Integer... positionId) {
        List<Position> listOfPositions = new ArrayList<>();
        List<Integer> listOfIds = new ArrayList<>();
        for(int i = 0; i < positionId.length; i++){
            listOfPositions.add(positionRepository.findById(positionId[i]).orElse(null));
            listOfIds.add(positionId[i]);
        }
        return Order.builder()

                .addressToDeliver(address)
                .positionIds(listOfIds)
                .price(listOfPositions.stream()
                        .mapToDouble(Position::getPositionPrice)
                        .sum())
                .orderStatus(OrderStatus.SETTED)
                .customerNeedChange(needChange)
                .changeFrom(cash-listOfPositions.stream()
                        .mapToDouble(Position::getPositionPrice)
                        .sum())
                .build();
    }
    public Order createOrder(Long orderId, OrderStatus createStatus) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        //payment option must be added here
        currentOrder.setOrderStatus(OrderStatus.CREATED);
        currentOrder.setOrderPlacedAt(Timestamp.valueOf(LocalDateTime.now()));
        return orderRepository.save(currentOrder);
    }

    public Order acceptOrder(Long orderId) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        //move to kitchen, make via targeted popup to employee needed
        currentOrder.setOrderStatus(OrderStatus.ACCEPTED);
        return orderRepository.save(currentOrder);
    }

    public Order cancelOrder(Long orderId) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        currentOrder.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(currentOrder);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public void deleteAllCancelledOrders() {
        List<Order> allCancelledOrders = orderRepository.findAll()
                .stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.CANCELLED)
                .toList();
        for(int i=0; i < allCancelledOrders.size(); i++){
            orderRepository.deleteById(allCancelledOrders.get(i).getOrderId());
        }
    }

    public Order setOrderTossedToDeliveryTimestamp(Long orderId) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        assert currentOrder != null;
        currentOrder.setOrderTossedToDelivery(Timestamp.valueOf(LocalDateTime.now()));
        return orderRepository.save(currentOrder);
    }

    public Order setOrderDeliveredTimestamp(Long orderId) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        assert currentOrder != null;
        currentOrder.setOrderTossedToDelivery(Timestamp.valueOf(LocalDateTime.now()));
        return orderRepository.save(currentOrder);
    }

    public Order changeAddressToDeliver(Long orderId, String newAddress) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        assert currentOrder != null;
        currentOrder.setAddressToDeliver(newAddress);
        return orderRepository.save(currentOrder);
    }

    public Order setCourier(Long orderId) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        assert currentOrder != null;
        List<User> allAvailableRunners = userRepository.findAll()
                .stream()
                .filter(user -> user.getUserStatus() == UserStatus.COURIER)
                .filter(user -> user.getEmployeeState() == EmployeeState.AVAILABLE)
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

    public Order setDirectCourier(Long orderId, String courierId) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        assert currentOrder != null;
        currentOrder.setCourier(courierId);
        return orderRepository.save(currentOrder);
    }

    public Order changeOrderStatus(Long orderId, OrderStatus newStatus) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        assert currentOrder != null;
        currentOrder.setOrderStatus(newStatus);
        return orderRepository.save(currentOrder);
    }

    public Order customerNeedChange(Long orderId, boolean needChange) {
        Order currentOrder = orderRepository.findById(orderId).orElse(null);
        assert currentOrder != null;
        currentOrder.setCustomerNeedChange(needChange);
        return orderRepository.save(currentOrder);
    }
}
