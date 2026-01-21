package com.mealdelivery.food.structure.orders;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OrderTest {
    private Order order;
    private final Instant testTime = Instant.from(LocalDateTime.now());
    private List<Integer> positions = new ArrayList<>();

    @Before
    public void setUp() {
        Long id = 1L;
        positions.add(14);
        positions.add(42);
        String courierId = "1341L";
        order = new Order(id, testTime, null, null, "Hometown 15",
                          positions, courierId, 3456D, OrderStatus.CREATED, true, 5000);
    }

    @Test
    public void testOrderId() {
        Long expectedI = 1L;
        assertEquals(expectedI, order.getOrderId());
    }

    @Test
    public void testOrderPlacedAt() {
        assertEquals(testTime, order.getOrderPlacedAt());
    }

    @Test
    public void testOrderTossedToDelivery() {
        assertNull(order.getOrderTossedToDelivery());
    }

    @Test
    public void testOrderDelivered() {
        assertNull(order.getOrderDelivered());
    }

    @Test
    public void testAddressToDeliver() {
        assertEquals("Hometown 15", order.getAddressToDeliver());
    }

    @Test
    public void testPositionIds() {
        assertEquals(positions, order.getPositionIds());
    }

    @Test
    public void testPrice() {
        double expectedPrice = 3456D;
        double delta = 0.001D;
        assertEquals(expectedPrice, order.getPrice(), delta);
    }

    @Test
    public void testCourierId() {
        Long courierId = 1341L;
        assertEquals(courierId, order.getCourier());
    }

    @Test
    public void testOrderStatus() {
        assertEquals(OrderStatus.CREATED, order.getOrderStatus());
    }

    @Test
    public void testCustomerNeedChange() {
        assertTrue(order.isCustomerNeedChange());
    }

    @Test
    public void testChangeFrom() {
        double expectedCash = 5000;
        double delta = 0.001D;
        assertEquals(expectedCash, order.getChangeFrom(),delta);
    }
}
