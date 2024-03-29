package com.mealdelivery.food.structure.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    String id;
    String name;
    String email;
    Long phone;
    Map<Integer, String> address;
    String hashedPassword;
    UserStatus userStatus;
    List<Integer> orderIdList;
    EmployeeState employeeState;

    public User() {

    }

    public void setHashedPassword(String password) {
     //   this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}