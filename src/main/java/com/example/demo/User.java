package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")  // MongoDB collection name
public class User {

    @Id
    public String id;

    public String username;
    public String email;

    // Getters and setters (optional for now)
}
