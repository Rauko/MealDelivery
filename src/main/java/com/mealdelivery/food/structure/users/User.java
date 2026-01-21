package com.mealdelivery.food.structure.users;

import com.mealdelivery.food.structure.delivery.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private Long id;

    private String name;
    private String email;
    private Long phone;

    private Address address;

    private String hashedPassword;
    private UserStatus userStatus;

    private List<Integer> orderIdList;

    private EmployeeState employeeState;
}