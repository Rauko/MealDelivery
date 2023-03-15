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

    public String getName(Long userId) {
        User localUser = userRepository.findById(userId).orElse(null);
        return localUser.getName();
    }

    public String getEmail(Long userId) {
        User localUser = userRepository.findById(userId).orElse(null);
        return localUser.getEmail();
    }

    public Long getPhone(Long userId) {
        User localUser = userRepository.findById(userId).orElse(null);
        return localUser.getPhone();
    }

    public UserStatus getUserStatus(Long userId) {
        User localUser = userRepository.findById(userId).orElse(null);
        return localUser.getUserStatus();
    }

    public Map<Integer,String> getAddress(Long userId) {
        User localUser = userRepository.findById(userId).orElse(null);
        return localUser.getAddress();
    }

    public String getHashedPassword(Long userId) {
        User localUser = userRepository.findById(userId).orElse(null);
        return localUser.getHashedPassword();
    }

    public EmployeeState getEmployeeState(Long userId) {
        User localUser = userRepository.findById(userId).orElse(null);
        return localUser.getEmployeeState();
    }

    public User createUser(String name, String email, Long phone, String address, String password) {
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

    public void setName(Long orderId, String newName) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setName(newName);
        userRepository.save(currentUser);
    }

    public void setEmail(Long orderId, String newEmail) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setEmail(newEmail);
        userRepository.save(currentUser);
    }

    public void setUserStatus(Long orderId, UserStatus newUserStatus) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setUserStatus(newUserStatus);
        userRepository.save(currentUser);
    }

    public void setPhone(Long orderId, Long newPhone) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setPhone(newPhone);
        userRepository.save(currentUser);
    }

    public void setHashedPassword(Long orderId, String newPassword) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        assert currentUser != null;
        currentUser.setHashedPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        userRepository.save(currentUser);
    }

    public void setAddress(Long orderId, String newAddress) {
        User currentUser = userRepository.findById(orderId).orElse(null);
        Map<Integer, String> currentAddress = new HashMap<Integer, String>();
        assert currentUser != null;
        currentAddress.putIfAbsent(currentUser.getAddress().size() + 1, newAddress);
        currentUser.setAddress(currentAddress);
        userRepository.save(currentUser);
    }
}
