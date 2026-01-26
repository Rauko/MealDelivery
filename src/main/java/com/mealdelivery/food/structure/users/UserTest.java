package com.mealdelivery.food.structure.users;

import com.mealdelivery.food.structure.delivery.Address;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        Address address = new Address(
                "Ukraine",
                "Kyiv region",
                "Kyiv",
                "Main Street",
                "10",
                "25"
        );

        user = User.builder()
                .id(1L)
                .name("Degustier Fatso")
                .email("fatso@example.com")
                .phone(1234567890L)
                .address(address)
                .hashedPassword("BestPasswordEver")
                .userStatus(UserStatus.NEWLY_CREATED)
                .employeeState(EmployeeState.NOT_EMPLOYEE)
                .build();
    }

    @Test
    public void testGetId() {
        assertEquals(Long.valueOf(1L), user.getId());
    }
    @Test
    public void testGetName() {
        assertEquals("Degustier Fatso", user.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("fatso@example.com", user.getEmail());
    }

    @Test
    public void testGetPhone() {
        assertEquals(Long.valueOf(123456789L), user.getPhone());
    }

    @Test
    public void testGetAddress() {
        assertNotNull(user.getAddress());
        assertEquals("Kyiv", user.getAddress().getCity());
        assertEquals("Main Street", user.getAddress().getStreet());
    }

    @Test
    public void testGetHashedPassword() {
        assertEquals("BestPasswordEver", user.getHashedPassword());
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