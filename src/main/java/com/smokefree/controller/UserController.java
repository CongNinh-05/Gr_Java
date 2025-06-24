package com.smokefree.controller;

import com.smokefree.dto.UserDTO;
import com.smokefree.entity.User;
import com.smokefree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        try {
            User savedUser = userService.registerUser(userDTO);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}