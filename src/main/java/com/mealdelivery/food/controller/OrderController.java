package com.mealdelivery.food.controller;

import com.mealdelivery.food.service.OrderService;
import com.mealdelivery.food.structure.orders.Order;
import com.mealdelivery.food.structure.orders.OrderStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class OrderController {

    private OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        Order order = orderService.setOrder(
                request.getAddress(),
                request.isPayByCard(),
                request.isNeedChange(),
                request.getCash(),
                request.getPositionId());
        return ResponseEntity.ok(order);
    }

    @DeleteMapping ("/{orderId}/delete")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{orderId}/delivery/tossed")
    public ResponseEntity<Order> setOrderTossedToDeliveryTimestamp(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.setOrderTossedToDeliveryTimestamp(orderId));
    }

    @PostMapping("/{orderId}/delivered")
    public ResponseEntity<Order> setOrderDeliveredTimestamp(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.setOrderDeliveredTimestamp(orderId));
    }

    @PostMapping("/{orderId}/change-address")
    public ResponseEntity<Order> changeAddressToDeliver(@PathVariable Long orderId,
                                                        @RequestParam String newAddress) {
        return ResponseEntity.ok(orderService.changeAddressToDeliver(orderId, newAddress));
    }

    @PostMapping("/{orderId}/courier/random")
    public ResponseEntity<Order> setRandomCourier (@PathVariable Long orderId) {
        //random available courier will be setted for this order
        return ResponseEntity.ok(orderService.setCourier(orderId));
    }

    @PostMapping("/{orderId}/courier/direct")
    public ResponseEntity<Order> setDirectCourier (@PathVariable Long orderId,
                                                   @RequestParam String courierId) {
        return ResponseEntity.ok(orderService.setDirectCourier(orderId,courierId));
    }

    @PostMapping("/{orderId}/change-status")
    public ResponseEntity<Order> changeOrderStatus(@PathVariable Long orderId,
                                                   @RequestParam OrderStatus newStatus) {
        return ResponseEntity.ok(orderService.changeOrderStatus(orderId, newStatus));
    }

    @PostMapping("/{orderId}/change")
    public ResponseEntity<Order> customerNeedChange(@PathVariable Long orderId,
                                                    @RequestParam boolean needChange) {
        return ResponseEntity.ok().body(orderService.customerNeedChange(orderId, needChange));
    }

    @PostMapping("/{orderId}/accept")
    public ResponseEntity<Order> acceptOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.acceptOrder(orderId));
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.cancelOrder(orderId));
    }

    @DeleteMapping("/delete/all-cancelled-orders")
    public ResponseEntity<Order> deleteAllCancelledOrders() {
        orderService.deleteAllCancelledOrders();
        return ResponseEntity.noContent().build();
    }
}
