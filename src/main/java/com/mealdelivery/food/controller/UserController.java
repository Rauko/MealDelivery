package com.mealdelivery.food.controller;

import com.mealdelivery.food.service.UserService;
import com.mealdelivery.food.structure.users.User;
import com.mealdelivery.food.structure.users.UserStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.createUser(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getAddress(),
                request.getPassword()
        );
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId,
                                           @RequestBody UpdateUserRequest request) {
        if(request.getName() != null) {
            userService.setName(userId, request.getName());
        }
        if(request.getEmail() != null) {
            userService.setEmail(userId, request.getEmail());
        }
        if(request.getPhone() != null) {
            userService.setPhone(userId, request.getPhone());
        }
        if(request.getPassword() != null) {
            userService.setHashedPassword(userId, request.getPassword());
        }
        if(request.getAddress() != null) {
            userService.setAddress(userId, request.getAddress());
        }
        if(request.getStatus() != null) {
            userService.setUserStatus(userId, request.getStatus());
        }

        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<User> updateUserStatus(@PathVariable Long userId,
                                                 @RequestBody UserStatus newStatus) {
        userService.setUserStatus(userId, newStatus);
        return ResponseEntity.ok(userService.getUser(userId));
    }
}
