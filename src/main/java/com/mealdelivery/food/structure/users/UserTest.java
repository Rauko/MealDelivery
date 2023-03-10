package com.mealdelivery.food.structure.users;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        Map<Integer, String> sampleAddress = new HashMap<Integer,String>();
        List<Integer> orderIdList = new ArrayList<>();
        sampleAddress.putIfAbsent(1,"YammyTown");
        Long id = 1L;
        user = new User(id, "Fatso", "fatso@example.com", 1234567890,
                sampleAddress,"BestPasswordEver",
                UserStatus.NEWLY_CREATED,
                orderIdList,
                RunnerStatus.NOT_RUNNER);
    }

    @Test
    public void testGetName() {
        assertEquals("Fatso", user.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("fatso@example.com", user.getEmail());
    }

    @Test
    public void testGetPhone() {
        assertEquals((Integer) 1234567890, user.getPhone());
    }

    @Test
    public void testGetAddress() {
        assertEquals("YammyTown", user.getAddress().get(1));
    }

    @Test
    public void testGetHashedPassword() {
        //checking that it  is actually saved for now... no hashing
        String hashedPassword = "BestPasswordEver";
        assertEquals(hashedPassword, user.getHashedPassword());
    }
}
