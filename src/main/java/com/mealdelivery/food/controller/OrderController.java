package com.mealdelivery.food.controller;

import com.mealdelivery.food.service.OrderService;
import com.mealdelivery.food.structure.orders.Order;
import com.mealdelivery.food.structure.orders.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/orders/all")
    public ResponseEntity<List<Order>> getAllTasks() {
        List<Order> order = orderService.getAllOrders();
        return ResponseEntity.ok().body(order);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/set")
    public ResponseEntity<Order> setOrder(@RequestParam String address, boolean needChange,
                                             double cash, Integer... positionId) {
        Order order = orderService.setOrder(address, needChange, cash, positionId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/add/{orderId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long orderId) {
        Order order = orderService.createOrder(orderId,OrderStatus.CREATED);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/delete/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/orders/{orderId}/OrderTossedToDelivery")
    public ResponseEntity<Order> setOrderTossedToDeliveryTimestamp(@PathVariable Long orderId) {
        Order order = orderService.setOrderTossedToDeliveryTimestamp(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/OrderDelivered")
    public ResponseEntity<Order> setOrderDeliveredTimestamp(@PathVariable Long orderId) {
        Order order = orderService.setOrderDeliveredTimestamp(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/changeAddressToDeliver")
    public ResponseEntity<Order> changeAddressToDeliver(@PathVariable Long orderId,
                                                        @RequestParam String newAddress) {
        Order order = orderService.changeAddressToDeliver(orderId, newAddress);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/setRandomCourier")
    public ResponseEntity<Order> setRandomCourier (@PathVariable Long orderId) {
        //random available courier will be setted for this order
        Order order = orderService.setCourier(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/setDirectCourier")
    public ResponseEntity<Order> setDirectCourier (@PathVariable Long orderId,
                                                   @RequestParam String courierId) {
        Order order = orderService.setDirectCourier(orderId,courierId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/changeOrderStatus")
    public ResponseEntity<Order> changeOrderStatus(@PathVariable Long orderId,
                                                   @RequestParam OrderStatus newStatus) {
        Order order = orderService.changeOrderStatus(orderId, newStatus);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/customerNeedChange")
    public ResponseEntity<Order> customerNeedChange(@PathVariable Long orderId,
                                                    @RequestParam boolean needChange) {
        Order order = orderService.customerNeedChange(orderId, needChange);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/acceptOrder")
    public ResponseEntity<Order> acceptOrder(@PathVariable Long orderId) {
        Order order = orderService.acceptOrder(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/cancelOrder")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        Order order = orderService.cancelOrder(orderId);
        return ResponseEntity.ok().body(order);
    }

    @DeleteMapping("/orders/delete/allcancelledorders")
    public ResponseEntity<Order> deleteAllCancelledOrders() {
        orderService.deleteAllCancelledOrders();
        return ResponseEntity.ok().build();
    }
}
