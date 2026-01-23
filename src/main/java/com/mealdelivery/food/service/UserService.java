package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.UserRepository;
import com.mealdelivery.food.structure.delivery.Address;
import com.mealdelivery.food.structure.users.EmployeeState;
import com.mealdelivery.food.structure.users.User;
import com.mealdelivery.food.structure.users.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }

    public User getUser(Long userId) {
        return getUserOrThrow(userId);
    }

    public User createUser(String name,
                           String email,
                           Long phone,
                           Address address,
                           String rawPassword) {
        User user = User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .address(address)
                .hashedPassword(rawPassword) //need be replaced with password encoder
                .userStatus(UserStatus.NEWLY_CREATED)
                .employeeState(EmployeeState.NOT_EMPLOYEE)
                .build();
        return userRepository.insert(user);
    }

    public User deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
        userRepository.deleteById(userId);
    }

    public User setName(Long userId, String newName) {
        User user = getUserOrThrow(userId);
        user.setName(newName);
        return userRepository.save(user);
    }

    public User setEmail(Long userId, String newEmail) {
        User user = getUserOrThrow(userId);
        user.setEmail(newEmail);
        return userRepository.save(user);
    }

    public User setPhone(Long userId, Long phone) {
        User user = getUserOrThrow(userId);
        user.setPhone(phone);
        return userRepository.save(user);
    }

    public User setAddress(Long userId, Address newAddress) {
        User user = getUserOrThrow(userId);
        user.setAddress(newAddress);
        return userRepository.save(user);
    }

    public User setHashedPassword(Long userId, String newPassword) {
        User user = getUserOrThrow(userId);
        user.setHashedPassword(newPassword); //PasswordEncoder
        return userRepository.save(user);
    }

    public User setUserStatus(Long userId, UserStatus newUserStatus) {
        User user = getUserOrThrow(userId);
        user.setUserStatus(newUserStatus);
        return userRepository.save(user);
    }
}
