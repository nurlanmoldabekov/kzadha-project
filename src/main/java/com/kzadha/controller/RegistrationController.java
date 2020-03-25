package com.kzadha.controller;

import com.kzadha.model.User;
import com.kzadha.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    private RegistrationService registrationService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity registerUser(@Valid @RequestBody User createUserData) {
        LOGGER.debug("registering user {}", createUserData);
        if (registrationService.isUserExists(createUserData.getEmail())) {
            return new ResponseEntity("Пользователь уже существует", HttpStatus.CONFLICT);
        }

        registrationService.registerUser(createUserData);
        return new ResponseEntity("OK", HttpStatus.OK);
    }


}
