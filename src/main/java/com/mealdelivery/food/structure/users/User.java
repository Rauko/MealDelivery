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

    public User(String name, String email, Integer phone, String hashedPassword) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.hashedPassword = hashedPassword;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String password) {
        this.hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());;
    }
}