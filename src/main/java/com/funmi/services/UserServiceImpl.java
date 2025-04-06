package com.funmi.services;

import com.funmi.data.models.User;
import com.funmi.data.repositories.UserRepository;
import com.funmi.DTO.requests.CreateSignupRequest;
import com.funmi.DTO.requests.LoginRequest;
import com.funmi.DTO.response.CreateSignupResponse;
import com.funmi.DTO.response.LoginResponse;
import com.funmi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Mapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateSignupResponse signup(CreateSignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        User user = mapper.mapUserRequest(request);
        userRepository.save(user);
        return new CreateSignupResponse(true, "Signup successful");
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        User savedUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!savedUser.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new LoginResponse(true, "Login successful");
    }

}