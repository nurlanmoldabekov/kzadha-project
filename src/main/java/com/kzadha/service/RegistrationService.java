package com.kzadha.service;

import com.kzadha.model.User;
import com.kzadha.model.json.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface RegistrationService {
    void registerUser(User user);
    boolean isUserExists(String email);
}
