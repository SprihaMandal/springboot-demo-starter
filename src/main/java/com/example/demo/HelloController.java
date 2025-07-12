package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
public class HelloController {

    @Autowired
    private UserRepository userRepository;

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
        return "Bye, " + user;
    }

    // @PostMapping("/register")
    // public String registerUser(@RequestBody User user) {
    // return "Registered: " + user.username + ", " + user.email;
    // }

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid User user) {
        userRepository.save(user);
        return "User registered in MongoDB: " + user.username;
    }

    // fetch user from mongo db
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

}