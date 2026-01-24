package com.mealdelivery.food;

import com.mealdelivery.food.dto.UserDTO;
import com.mealdelivery.food.structure.users.User;

public class UserMapper {

    private UserMapper() {}

    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .userStatus(user.getUserStatus())
                .employeeState(user.getEmployeeState())
                .build();
    }
}
