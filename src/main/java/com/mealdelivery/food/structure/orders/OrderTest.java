package com.mealdelivery.food.structure.orders;

import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OrderTest {
    private Order order;
    private Instant testTime;
    private List<Integer> positions;

    @Before
    public void setUp() {

        testTime = Instant.now();

        positions = new ArrayList<>();
        positions.add(14);
        positions.add(42);

        order = Order.builder()
                .orderId(1L)
                .orderPlacedAt(testTime)
                .orderTossedToDelivery(null)
                .orderDelivered(null)
                .addressToDeliver("Hometown 15")
                .positionIds(positions)
                .courier("1341")
                .price(3456D)
                .orderStatus(OrderStatus.CREATED)
                .customerNeedChange(true)
                .changeFrom(5000D)
                .payByCard(false)
                .build();
    }

    @Test
    public void testOrderId() {
        assertEquals(Long.valueOf(1L), order.getOrderId());
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
        assertEquals("1341", order.getCourier());
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
