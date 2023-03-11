package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.UserRepository;
import com.mealdelivery.food.structure.users.EmployeeState;
import com.mealdelivery.food.structure.users.User;
import com.mealdelivery.food.structure.users.UserStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createUser(String name, String email, Integer phone, String address, String password) {
        Map<Integer, String> initialAddress = new HashMap<Integer, String>();
        initialAddress.putIfAbsent(0, address);
        return User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .userStatus(UserStatus.NEWLY_CREATED)
                .address(initialAddress)
                .hashedPassword(BCrypt.hashpw(password, BCrypt.gensalt()))
                .employeeState(EmployeeState.NOT_EMPLOYEE)
                .build();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User setName(Long orderId, String newName) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setName(newName);
        return userRepository.save(currentUser);
    }

    public User setEmail(Long orderId, String newEmail) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setEmail(newEmail);
        return userRepository.save(currentUser);
    }

    public User setUserStatus(Long orderId, UserStatus newUserStatus) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setUserStatus(newUserStatus);
        return userRepository.save(currentUser);
    }

    public User setPhone(Long orderId, Integer newPhone) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setPhone(newPhone);
        return userRepository.save(currentUser);
    }

    public User setHashedPassword(Long orderId, String newPassword) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setHashedPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        return userRepository.save(currentUser);
    }

    public User setAddress(Long orderId, String newAddress) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        Map<Integer, String> currentAddress = new HashMap<Integer, String>();
        assert currentUser != null;
        currentAddress.putIfAbsent(currentUser.getAddress().size() + 1, newAddress);
        currentUser.setAddress(currentAddress);
        return userRepository.save(currentUser);
    }
}
