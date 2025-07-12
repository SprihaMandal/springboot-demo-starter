package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Document(collection = "users")  // MongoDB collection name
public class User {

    @Id
    public String id;

    @NotBlank(message = "Username cannot be blank")
    public String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    public String email;

    // Getters and setters (optional for now)
}
