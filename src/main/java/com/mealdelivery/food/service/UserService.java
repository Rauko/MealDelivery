package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.UserRepository;
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

    public User getUser(Integer userId) {
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
                .build();
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public User setName(Integer orderId, String newName) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setName(newName);
        return userRepository.save(currentUser);
    }

    public User setEmail(Integer orderId, String newEmail) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setEmail(newEmail);
        return userRepository.save(currentUser);
    }

    public User setUserStatus(Integer orderId, UserStatus newUserStatus) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setUserStatus(newUserStatus);
        return userRepository.save(currentUser);
    }

    public User setPhone(Integer orderId, Integer newPhone) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setPhone(newPhone);
        return userRepository.save(currentUser);
    }

    public User setHashedPassword(Integer orderId, String newPassword) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setHashedPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        return userRepository.save(currentUser);
    }

    public User setAddress(Integer orderId, String newAddress) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        Map<Integer, String> currentAddress = new HashMap<Integer, String>();
        assert currentUser != null;
        currentAddress.putIfAbsent(currentUser.getAddress().size() + 1, newAddress);
        currentUser.setAddress(currentAddress);
        return userRepository.save(currentUser);
    }
}
