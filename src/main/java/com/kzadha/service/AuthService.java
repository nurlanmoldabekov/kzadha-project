package com.kzadha.service;


import com.kzadha.model.User;
import com.kzadha.model.json.JwtAuthenticationResponse;
import com.kzadha.model.json.LoginRequest;

public interface AuthService {
    JwtAuthenticationResponse authUser(LoginRequest loginRequest);
    JwtAuthenticationResponse authUserWithoutCredentials(User user);
}
