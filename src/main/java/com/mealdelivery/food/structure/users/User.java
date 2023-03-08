package com.mealdelivery.food.structure.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
    String hashedPassword;
    UserStatus userStatus;

    public void setHashedPassword(String password) {
        this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());;
    }
}