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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        user.setId("1");
        user.setName("Degustier Fatso");
        user.setEmail("fatso@example.com");
        Long phoneNumberInLong = 1234567890L;
        user.setPhone(phoneNumberInLong);
        user.setAddress(sampleAddress);
        user.setHashedPassword("BestPasswordEver");

        when(userService.createUser(anyString(), anyString(), anyLong(), anyString(), anyString())).thenReturn(user);

        User createdUser = controller.createUser("Degustier Fatso", "fatso@example.com", phoneNumberInLong,
                "Yammy Town", "BestPasswordEver").getBody();

        assert createdUser != null;
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
        user1.setId("1");

        User user2 = new User();
        user2.setId("2");

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

    @Test
    public void testGetUser() {
        User user = new User();
        String userId = "1";
        Map<Integer, String> sampleAddress = new HashMap<Integer,String>();
        sampleAddress.putIfAbsent(1,"Yammy Town");
        user.setId(userId);
        user.setName("Degustier Fatso");
        user.setEmail("fatso@example.com");
        Long phoneNumberInLong = 1234567890L;
        user.setPhone(phoneNumberInLong);
        user.setAddress(sampleAddress);
        user.setHashedPassword("BestPasswordEver");


        Mockito.when(userService.getUser(String.valueOf(userId))).thenReturn(user);
        ResponseEntity<User> responseEntity = controller.getUser(String.valueOf(userId));

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<Integer,String> expectedAddress = new HashMap<>();
        expectedAddress.putIfAbsent(1,"Yammy Town");
        String expectedHashedPassword = user.getHashedPassword();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        User result = responseEntity.getBody();
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Degustier Fatso", result.getName());
        assertEquals("fatso@example.com", result.getEmail());
        assertEquals((Long) 1234567890L, result.getPhone());
        assertEquals(expectedAddress, result.getAddress());
        assertEquals(expectedHashedPassword, result.getHashedPassword());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId("1");

        when(userService.getUser("1")).thenReturn(user);

        ResponseEntity<Object> responseEntity = controller.deleteUser("1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(userService, times(1)).deleteUser("1");
    }
/*
    @Test
    public void testSetName() {
        User user = new User();
        Long userId = 1L;
        user.setId(userId);
        user.setName("Degustier Fatso");

        when(userService.setName(anyLong(), anyString())).thenReturn(user);

        ResponseEntity<User> response = controller.setName(userId,"New Name");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        User updatedUser = response.getBody();

        assertNotNull(updatedUser);
        assertEquals("New Name", updatedUser.getName());
    }

    @Test
    public void testSetEmail() {
        User user = new User();
        Long userId = 1L;
        user.setId(userId);
        user.setEmail("fatso@example.com");

        when(userService.setEmail(anyLong(), anyString())).thenReturn(user);

        ResponseEntity<User> response = controller.setName(userId,"fatso-nutso@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        User updatedUser = response.getBody();

        assertNotNull(updatedUser);
        assertEquals("fatso-nutso@example.com", updatedUser.getEmail());
    }*/
}
