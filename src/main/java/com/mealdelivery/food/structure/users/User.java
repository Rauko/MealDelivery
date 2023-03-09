package com.mealdelivery.food.structure.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Map;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    Integer id;
    String name;
    String email;
    Integer phone;
    Map<Integer, String> address;
    String hashedPassword;
    UserStatus userStatus;
    List<Integer> orderIdList;

    public void setHashedPassword(String password) {
        this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());;
    }
}