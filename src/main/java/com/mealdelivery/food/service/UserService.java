package com.mealdelivery.food.service;

import com.mealdelivery.food.persistance.UserRepository;
import com.mealdelivery.food.structure.delivery.Address;
import com.mealdelivery.food.structure.users.EmployeeState;
import com.mealdelivery.food.structure.users.User;
import com.mealdelivery.food.structure.users.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
                           String newRawPassword) {

        String encodedPassword = passwordEncoder.encode(newRawPassword);

        User user = User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .address(address)
                .hashedPassword(encodedPassword) //need be replaced with password encoder
                .userStatus(UserStatus.NEWLY_CREATED)
                .employeeState(EmployeeState.NOT_EMPLOYEE)
                .build();
        return userRepository.insert(user);
    }

    public void deleteUser(Long userId) {
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

    public User setHashedPassword(Long userId, String newRawPassword) {
        User user = getUserOrThrow(userId);

        String encodedPassword = passwordEncoder.encode(newRawPassword);
        user.setHashedPassword(encodedPassword);

        return userRepository.save(user);
    }

    public User setUserStatus(Long userId, UserStatus newUserStatus) {
        User user = getUserOrThrow(userId);
        user.setUserStatus(newUserStatus);
        return userRepository.save(user);
    }
}
