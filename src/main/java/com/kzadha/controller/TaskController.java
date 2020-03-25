package com.kzadha.controller;

import com.kzadha.model.enums.TaskStatus;
import com.kzadha.model.json.CreateTaskRequest;
import com.kzadha.service.TaskService;
import com.kzadha.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskService service;

    @GetMapping("")
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('SIGNER', 'WORKER', 'ADMIN')")
    public ResponseEntity<?> create(@RequestBody CreateTaskRequest request) {

        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/sign")
    @PreAuthorize("hasAnyRole('SIGNER')")
    public ResponseEntity<?> sign(@RequestParam Long taskId, @RequestParam Long userId) {
        service.signTask(taskId, userId);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/set-executor")
    @PreAuthorize("hasAnyRole('SIGNER', 'WORKER', 'ADMIN')")
    public ResponseEntity<?> setExecutor(@RequestParam Long taskId, @RequestParam Long userId) {
        service.setExecutor(userId, taskId);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/move")
    @PreAuthorize("hasAnyRole('SIGNER', 'WORKER', 'ADMIN')")
    public ResponseEntity<?> move(@RequestParam Long taskId, @RequestParam TaskStatus status) {
        service.move(taskId, status);
        return ResponseEntity.ok("OK");
    }


}
