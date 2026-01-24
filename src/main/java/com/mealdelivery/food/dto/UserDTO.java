package com.mealdelivery.food.dto;

import com.mealdelivery.food.structure.delivery.Address;
import com.mealdelivery.food.structure.users.EmployeeState;
import com.mealdelivery.food.structure.users.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;
    private String email;
    private Long phone;

    private Address address;

    private UserStatus userStatus;
    private EmployeeState employeeState;
}
