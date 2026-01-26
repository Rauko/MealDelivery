package com.mealdelivery.food.controller;

import com.mealdelivery.food.dto.UserDTO;
import com.mealdelivery.food.service.UserService;
import com.mealdelivery.food.structure.delivery.Address;
import com.mealdelivery.food.structure.users.EmployeeState;
import com.mealdelivery.food.structure.users.User;
import com.mealdelivery.food.structure.users.UserStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController controller;

    @Test
    public void createUser_shouldReturnUserDTO() {

        Address address = new Address(
                "Ukraine",
                "Kyiv region",
                "Kyiv",
                "Main Street",
                "10",
                "25"
        );

        User user = User.builder()
                .id(1L)
                .name("Degustier Fatso")
                .email("fatso@example.com")
                .phone(1234567890L)
                .address(address)
                .userStatus(UserStatus.NEWLY_CREATED)
                .employeeState(EmployeeState.NOT_EMPLOYEE)
                .build();

        when(userService.createUser(
                any(),
                any(),
                anyLong(),
                any(),
                any()
        )).thenReturn(user);

        CreateUserRequest request = new CreateUserRequest();
        request.setName("Degustier Fatso");
        request.setEmail("fatso@example.com");
        request.setPhone(1234567890L);
        request.setAddress(address);
        request.setPassword("12345");

        ResponseEntity<UserDTO> response = controller.createUser(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        UserDTO dto = response.getBody();

        assertEquals("Degustier Fatso", dto.getName());
        assertEquals("fatso@example.com", dto.getEmail());
        assertEquals(Long.valueOf(1234567890L), dto.getPhone());
        assertEquals(address, dto.getAddress());
        assertEquals(UserStatus.NEWLY_CREATED, dto.getUserStatus());
    }

    @Test
    public void getAllUsers_shouldReturnList() {
        User user1 = User.builder().id(1L).build();
        User user2 = User.builder().id(2L).build();

        when(userService.getAllUsers()).
                thenReturn(List.of(user1, user2));

        ResponseEntity<List<UserDTO>> response = controller.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void getUser_shouldReturnUserDTO() {

        User user = User.builder()
                .id(1L)
                .name("Degustier Fatso")
                .build();

        when(userService.getUser(1L)).thenReturn(user);

        ResponseEntity<UserDTO> response = controller.getUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Degustier Fatso", response.getBody().getName());
    }

    @Test
    public void deleteUser_shouldReturnNoContent() {
       ResponseEntity<Void> response = controller.deleteUser(1L);

       assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
       verify(userService, times(1)).deleteUser(1L);
    }
}
