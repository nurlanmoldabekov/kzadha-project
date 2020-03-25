package com.kzadha.controller;

import com.kzadha.model.json.CreateTaskRequest;
import com.kzadha.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CreateTaskRequest request) {

        return ResponseEntity.ok(userService.getAll());
    }


}
