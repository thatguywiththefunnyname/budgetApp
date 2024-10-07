package com.project.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.practice.entities.Users;
import com.project.practice.service.UserService;

@RestController
@RequestMapping("/users")
public class Controller {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public Controller(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/{id}/changePassword")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        if (userService.changePassword(id, oldPassword, newPassword)) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.badRequest()
                    .body("Invalid password./nPassword must be:/nMinimum 8 characters/nContain 1 special character");
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Users user) {
        if (userService.checkOriginalPassword(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            return ResponseEntity.ok("User added successfully");
        } else {
            return ResponseEntity.badRequest()
                    .body("Password must be:/nMinimum 8 characters/nContain 1 special character");
        }
    }

}