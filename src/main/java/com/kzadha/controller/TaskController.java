package com.kzadha.controller;

import com.kzadha.model.Task;
import com.kzadha.model.enums.TaskStatus;
import com.kzadha.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task")
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
    public ResponseEntity<?> create(@RequestBody Task request) {

        return ResponseEntity.ok(service.create(request));
    }

    @PostMapping("/sign/{id}")
    @PreAuthorize("hasAnyRole('SIGNER')")
    public ResponseEntity<?> sign(@PathVariable Long id) {
        service.signTask(id);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/set-executor")
    @PreAuthorize("hasAnyRole('SIGNER', 'WORKER', 'ADMIN')")
    public ResponseEntity<?> setExecutor(@RequestParam Long taskId, @RequestParam Long userId) {
        service.setExecutor(userId, taskId);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/move")
    @PreAuthorize("hasAnyRole('SIGNER', 'WORKER', 'ADMIN')")
    public ResponseEntity<?> move(@RequestParam Long taskId, @RequestParam TaskStatus status) {
        service.move(taskId, status);
        return ResponseEntity.ok("OK");
    }


}
