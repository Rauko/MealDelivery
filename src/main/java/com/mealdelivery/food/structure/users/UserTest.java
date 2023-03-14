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
    private String hashedPassword;
    private Map<Integer, String> sampleAddress = new HashMap<Integer,String>();
    private List<Integer> orderIdList = new ArrayList<>();

    @Before
    public void setUp() {
        sampleAddress.putIfAbsent(1,"Yammy Town");
        Long id = 1L;
        user = new User(id, "Degustier Fatso",
                            "fatso@example.com",
                            1234567890L,
                                  sampleAddress,
                     null,
                                  UserStatus.NEWLY_CREATED,
                                  orderIdList,
                                  EmployeeState.NOT_EMPLOYEE);
        user.setHashedPassword("BestPasswordEver");
        hashedPassword = user.getHashedPassword();
    }

    @Test
    public void testGetId() {
        Long expectedId = 1L;
        assertEquals(expectedId, user.getId());
    }
    @Test
    public void testGetName() {
        assertEquals("Degustier Fatso", user.getName());
    }

    @Test
    public void testOrderList() {
        List<Integer> expectedOrderIdList = new ArrayList<>();
        assertEquals(expectedOrderIdList, user.getOrderIdList());
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
        assertEquals("Yammy Town", user.getAddress().get(1));
    }

    @Test
    public void testGetHashedPassword() {
        //checking that it is actually saved for now... no hashing
        assertEquals(hashedPassword, user.getHashedPassword());
    }

    @Test
    public void testGetEmployeeState() {
        assertEquals(EmployeeState.NOT_EMPLOYEE, user.getEmployeeState());
    }

    @Test
    public void testUserStatus() {
        assertEquals(UserStatus.NEWLY_CREATED, user.getUserStatus());
    }
}