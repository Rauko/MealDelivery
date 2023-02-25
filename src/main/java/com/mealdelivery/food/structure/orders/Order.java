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
    List<String> positions;
    Runner courier;
    double price;

    String orderStatus;

    public Integer getOrderId() {
        return orderId;
    }

    public Timestamp getOrderPlacedAt() {
        return orderPlacedAt;
    }

    public void setOrderPlacedAt(Timestamp orderPlacedAt) {
        this.orderPlacedAt = orderPlacedAt;
    }

    public Timestamp getOrderTossedToDelivery() {
        return orderTossedToDelivery;
    }

    public void setOrderTossedToDelivery(Timestamp orderTossedToDelivery) {
        this.orderTossedToDelivery = orderTossedToDelivery;
    }

    public Timestamp getOrderDelivered() {
        return orderDelivered;
    }

    public void setOrderDelivered(Timestamp orderDelivered) {
        this.orderDelivered = orderDelivered;
    }

    public String getAddressToDeliver() {
        return addressToDeliver;
    }

    public void setAddressToDeliver(String addressToDeliver) {
        this.addressToDeliver = addressToDeliver;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public Runner getCourier() {
        return courier;
    }

    public void setCourier(Runner courier) {
        this.courier = courier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
