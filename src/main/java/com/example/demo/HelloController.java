package com.example.demo;

import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {

    // Simple GET endpoint
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }

    // Another GET endpoint
    @GetMapping("/user")
    public String getUser() {
        return "User: Spriha";
    }

    // Endpoint with query parameter
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/bye")
    public String sayBye(@RequestParam String user) {
        return "Bye, "+ user;
    }

    
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return "Registered: " + user.username + ", " + user.email;
    }
    
    
}
