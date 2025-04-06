package com.funmi.controllers;

import com.funmi.DTO.requests.CreateSignupRequest;
import com.funmi.DTO.requests.LoginRequest;
import com.funmi.DTO.response.CreateSignupResponse;
import com.funmi.DTO.response.LoginResponse;
import com.funmi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<CreateSignupResponse> register(@Valid @RequestBody CreateSignupRequest request) {
        CreateSignupResponse response = userService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);

        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.ok(response);
    }


}
