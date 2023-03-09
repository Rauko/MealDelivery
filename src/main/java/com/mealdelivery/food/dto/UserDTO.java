package com.mealdelivery.food.dto;

import com.mealdelivery.food.structure.delivery.Address;
import com.mealdelivery.food.structure.users.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    private Integer phone;
    private List<Address> address;
    private String hashedPassword;
    private UserStatus userStatus;
}
