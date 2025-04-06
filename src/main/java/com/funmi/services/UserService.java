package com.funmi.services;

import com.funmi.DTO.requests.CreateSignupRequest;
import com.funmi.DTO.requests.LoginRequest;
import com.funmi.DTO.response.CreateSignupResponse;
import com.funmi.DTO.response.LoginResponse;

public interface UserService {
    CreateSignupResponse signup(CreateSignupRequest request);
    LoginResponse login(LoginRequest request);
}

