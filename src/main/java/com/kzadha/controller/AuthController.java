package com.kzadha.controller;

import com.kzadha.model.json.LoginRequest;
import com.kzadha.security.JwtTokenProvider;
import com.kzadha.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authService.authUser(loginRequest));
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<?> check(@RequestBody String accessToken) {
        ResponseEntity<String> result = null;
        if (tokenProvider.validateToken(accessToken)) {
            result = new ResponseEntity("OK", HttpStatus.OK);
        } else {
            result = new ResponseEntity("FAIL", HttpStatus.UNAUTHORIZED);
        }

        return result;
    }

}
