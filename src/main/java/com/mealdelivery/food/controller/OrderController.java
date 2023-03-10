package com.mealdelivery.food.controller;

import com.mealdelivery.food.service.OrderService;
import com.mealdelivery.food.structure.orders.Order;
import com.mealdelivery.food.structure.orders.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<Order> getOrder(@PathVariable Integer orderId) {
        Order order = orderService.getOrder(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/add")
    public ResponseEntity<Order> createOrder(@RequestParam String address, boolean needChange,
                                             double cash, Integer... positionId) {
        Order order = orderService.createOrder(address, needChange, cash, positionId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/delete/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/orders/{orderId}/OrderTossedToDelivery")
    public ResponseEntity<Order> setOrderTossedToDeliveryTimestamp(@PathVariable Integer orderId) {
        Order order = orderService.setOrderTossedToDeliveryTimestamp(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/OrderDelivered")
    public ResponseEntity<Order> setOrderDeliveredTimestamp(@PathVariable Integer orderId) {
        Order order = orderService.setOrderDeliveredTimestamp(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/changeAddressToDeliver/{newAddress}")
    public ResponseEntity<Order> changeAddressToDeliver(@PathVariable Integer orderId, @RequestParam String newAddress) {
        Order order = orderService.changeAddressToDeliver(orderId, newAddress);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/changeAddressToDeliver/{courier}")
    public ResponseEntity<Order> setCourier (@PathVariable Integer orderId) {
        //random available courier will be setted for this order
        Order order = orderService.setCourier(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/changeOrderStatus/{newStatus}")
    public ResponseEntity<Order> changeOrderStatus(@PathVariable Integer orderId, @RequestParam OrderStatus newStatus) {
        Order order = orderService.changeOrderStatus(orderId, newStatus);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders/{orderId}/customerNeedChange/{needChange}")
    public ResponseEntity<Order> customerNeedChange(@PathVariable Integer orderId, @RequestParam boolean needChange) {
        Order order = orderService.customerNeedChange(orderId, needChange);
        return ResponseEntity.ok().body(order);
    }
}
