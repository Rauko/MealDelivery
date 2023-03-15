package com.mealdelivery.food.controller;

import com.mealdelivery.food.service.UserService;
import com.mealdelivery.food.structure.users.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController controller;

    @Test
    public void testCreateUser() {
        User user = new User();
        Map<Integer, String> sampleAddress = new HashMap<Integer,String>();
        sampleAddress.putIfAbsent(1,"Yammy Town");
        user.setId(1L);
        user.setName("Degustier Fatso");
        user.setEmail("fatso@example.com");
        Long phoneNumberInLong = 1234567890L;
        user.setPhone(phoneNumberInLong);
        user.setAddress(sampleAddress);
        user.setHashedPassword("BestPasswordEver");

        when(userService.createUser(anyString(), anyString(), anyLong(), anyString(), anyString())).thenReturn(user);

        User createdUser = controller.createUser("Degustier Fatso", "fatso@example.com", phoneNumberInLong,
                "Yammy Town", "BestPasswordEver").getBody();

        String password = createdUser.getHashedPassword();

        assertNotNull(createdUser.getId());
        assertEquals("Degustier Fatso", createdUser.getName());
        assertEquals("fatso@example.com", createdUser.getEmail());
        assertEquals(phoneNumberInLong, createdUser.getPhone());
        assertEquals("Yammy Town", createdUser.getAddress().get(1));
        assertEquals(password, createdUser.getHashedPassword());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        Map<Integer, String> sampleAddress = new HashMap<Integer,String>();
        sampleAddress.putIfAbsent(1,"Yammy Town");
        user1.setId(1L);
        user1.setName("Degustier Fatso");
        user1.setEmail("fatso@example.com");
        Long phoneNumberInLong = 1234567890L;
        user1.setPhone(phoneNumberInLong);
        user1.setAddress(sampleAddress);
        user1.setHashedPassword("BestPasswordEver");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Degustier Fatso");
        user2.setEmail("fatso@example.com");
        user2.setPhone(phoneNumberInLong);
        user2.setAddress(sampleAddress);
        user2.setHashedPassword("BestPasswordEver");

        List<User> expectedUsers = Arrays.asList(user1, user2);

        Mockito.when(userService.getAllUsers()).thenReturn(expectedUsers);

        ResponseEntity<List<User>> responseEntity = controller.getAllUsers();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<User> actualUsers = responseEntity.getBody();
        assertNotNull(actualUsers);

        assertEquals(expectedUsers.size(), actualUsers.size());
        assertTrue(expectedUsers.containsAll(actualUsers));
    }

}
