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

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PositionRepository positionRepository;
    private final UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private Order getOrderOrThrow(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }

    private Position getPositionOrThrow(Integer positionId) {
        return positionRepository.findById(positionId)
                .orElseThrow(() -> new IllegalArgumentException("Position not found: " + positionId));
    }

    public Order getOrder(Long orderId) {
        return getOrderOrThrow(orderId);
    }

    public Order setOrder(String address,
                          boolean payByCard,
                          boolean needChange,
                          double cash,
                          Integer... positionId
                          ) {
        List<Integer> ids = Arrays.asList(positionId);

        List<Position> positions = ids.stream()
                .map(this::getPositionOrThrow)
                .toList();

        double totalPrice = positions.stream()
                .mapToDouble(Position::getPositionPrice)
                .sum();

            Order order = Order.builder()
                    .addressToDeliver(address)
                    .positionIds(ids)
                    .price(totalPrice)
                    .orderStatus(OrderStatus.SETTED)
                    .payByCard(payByCard)
                    .customerNeedChange(needChange)
                    .changeFrom(cash-totalPrice)
                    .build();

            return orderRepository.insert(order);
        }

        public Order createOrder(Long orderId, OrderStatus status) {
            Order order = getOrderOrThrow(orderId);

            order.setOrderStatus(status);
            order.setOrderPlacedAt(Instant.now());

            return orderRepository.save(order);
        }

        public Order acceptOrder(Long orderId) {
            Order order = getOrderOrThrow(orderId);
            order.setOrderStatus(OrderStatus.ACCEPTED);
            return orderRepository.save(order);
        }

        public Order cancelOrder(Long orderId) {
            Order order = getOrderOrThrow(orderId);
            order.setOrderStatus(OrderStatus.CANCELLED);
            return orderRepository.save(order);
        }

        public void deleteOrder(Long orderId) {
            if(!orderRepository.existsById(orderId)) {
                throw new IllegalArgumentException("Order not found: " + orderId);
            }
            orderRepository.deleteById(orderId);
        }

        public void deleteAllCanceledOrders() {
            List<Order> cancelled = orderRepository.findAll()
                    .stream()
                    .filter(o -> o.getOrderStatus() == OrderStatus.CANCELLED)
                    .toList();

            cancelled.forEach(o -> orderRepository.deleteById(o.getOrderId()));
        }

        public Order setOrderTossedToDeliveryTimestamp(Long orderId) {
            Order order = getOrderOrThrow(orderId);
            order.setOrderTossedToDelivery(Instant.now());
            order.setOrderStatus(OrderStatus.DELIVERY);
            return orderRepository.save(order);
        }

        public Order setOrderDeliveredTimestamp(Long orderId) {
            Order order = getOrderOrThrow(orderId);
            order.setOrderDelivered(Instant.now());
            order.setOrderStatus(OrderStatus.COMPLETED);
            return orderRepository.save(order);
        }

        public Order changeAddressToDeliver(Long orderId, String newAddress) {
            Order order = getOrderOrThrow(orderId);

            if(order.getOrderTossedToDelivery() != null) {
                throw new IllegalArgumentException
                        ("Cannot change delivery address: order already tossed to delivery.");
            }

            order.setAddressToDeliver(newAddress);
            return orderRepository.save(order);
        }

        public Order setCourier(Long orderId) {
            Order order = getOrderOrThrow(orderId);

            List<User> couriers = userRepository.findAll()
                    .stream()
                    .filter(u -> u.getUserStatus() == UserStatus.COURIER)
                    .filter(u -> u.getEmployeeState() == EmployeeState.AVAILABLE)
                    .toList();

            if(couriers.isEmpty()) {
                throw new IllegalArgumentException("No available courier right now.");
            }

            User courier = couriers.get(new Random().nextInt(couriers.size()));

            order.setCourier(String.valueOf(courier.getId()));

            return orderRepository.save(order);
        }

        public Order setDirectCourier(Long orderId,String courierId) {
            Order order = getOrderOrThrow(orderId);
            order.setCourier(courierId);
            return orderRepository.save(order);
        }

        public Order changeOrderStatus(Long orderId, OrderStatus newStatus) {
            Order order = getOrderOrThrow(orderId);
            order.setOrderStatus(newStatus);
            return orderRepository.save(order);
        }

        public Order customerNeedChange(Long orderId, boolean needChange) {
            Order order = getOrderOrThrow(orderId);
            order.setCustomerNeedChange(needChange);
            return orderRepository.save(order);
        }
}
