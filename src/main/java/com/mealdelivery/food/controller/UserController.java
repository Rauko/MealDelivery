package com.mealdelivery.food.controller;

import com.mealdelivery.food.service.UserService;
import com.mealdelivery.food.structure.users.User;
import com.mealdelivery.food.structure.users.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getOrder(@PathVariable Long userId) {
        User users = userService.getUser(userId);
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> createUser(@RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam Integer phone,
                                           @RequestParam String address,
                                           @RequestParam String password) {
        User user = userService.createUser(name, email, phone, address, password);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/editname")
    public ResponseEntity<List<User>> setName(@PathVariable Long userId,
                                              @RequestParam String newName) {
        userService.setName(userId, newName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/editemail")
    public ResponseEntity<List<User>> setEmail(@PathVariable Long userId,
                                               @RequestParam String newEmail) {
        userService.setEmail(userId, newEmail);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/editstatus")
    public ResponseEntity<List<User>> setUserStatus(@PathVariable Long userId,
                                                    @RequestParam UserStatus newUserStatus) {
        userService.setUserStatus(userId, newUserStatus);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/editphone")
    public ResponseEntity<List<User>> setPhone(@PathVariable Long userId,
                                               @RequestParam Integer newPhone) {
        userService.setPhone(userId, newPhone);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/editpassword")
    public ResponseEntity<List<User>> setHashedPassword(@PathVariable Long userId,
                                                        @RequestParam String newPassword) {
        userService.setHashedPassword(userId, newPassword);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/editaddress")
    public ResponseEntity<List<User>> setAddress(@PathVariable Long userId,
                                                 @RequestParam String newAddress) {
        userService.setAddress(userId, newAddress);
        return ResponseEntity.ok().build();
    }
}
