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
import java.util.Map;

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
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User users = userService.getUser(userId);
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> createUser(@RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam Long phone,
                                           @RequestParam String address,
                                           @RequestParam String password) {
        User user = userService.createUser(name, email, phone, address, password);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{userId}/setname")
    public ResponseEntity<User> setName(@PathVariable String userId,
                                        @RequestParam String newName) {
        userService.setName(userId, newName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{userId}/editemail")
    public ResponseEntity<List<User>> setEmail(@PathVariable String userId,
                                               @RequestParam String newEmail) {
        userService.setEmail(userId, newEmail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{userId}/editstatus")
    public ResponseEntity<List<User>> setUserStatus(@PathVariable String userId,
                                                    @RequestParam UserStatus newUserStatus) {
        userService.setUserStatus(userId, newUserStatus);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{userId}/editphone")
    public ResponseEntity<List<User>> setPhone(@PathVariable String userId,
                                               @RequestParam Long newPhone) {
        userService.setPhone(userId, newPhone);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{userId}/editpassword")
    public ResponseEntity<List<User>> setHashedPassword(@PathVariable String userId,
                                                        @RequestParam String newPassword) {
        userService.setHashedPassword(userId, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{userId}/editaddress")
    public ResponseEntity<List<User>> setAddress(@PathVariable String userId,
                                                 @RequestParam String newAddress) {
        userService.setAddress(userId, newAddress);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/getname")
    public ResponseEntity<String> getName(@PathVariable String userId) {
        String currentName = userService.getName(userId);
        return ResponseEntity.ok(currentName);
    }

    @GetMapping("/users/{userId}/getemail")
    public ResponseEntity<String> getEmail(@PathVariable String userId) {
        String currentEmail = userService.getEmail(userId);
        return ResponseEntity.ok(currentEmail);
    }

    @GetMapping("/users/{userId}/editstatus")
    public ResponseEntity<UserStatus> getUserStatus(@PathVariable String userId) {
        UserStatus currentStatus = userService.getUserStatus(userId);
        return ResponseEntity.ok(currentStatus);
    }

    @GetMapping("/users/{userId}/editphone")
    public ResponseEntity<Long> getPhone(@PathVariable String userId) {
        Long PhoneNumber = userService.getPhone(userId);
        return ResponseEntity.ok(PhoneNumber);
    }

    @GetMapping("/users/{userId}/gethashedpassword")
    public ResponseEntity<String> getHashedPassword(@PathVariable String userId) {
        String hashedPassword = userService.getHashedPassword(userId);
        return ResponseEntity.ok(hashedPassword);
    }

    @GetMapping("/users/{userId}/getaddress")
    public ResponseEntity<Map<Integer, String>> getAddress(@PathVariable String userId) {
        Map<Integer, String> address = userService.getAddress(userId);
        return ResponseEntity.ok(address);
    }
}
