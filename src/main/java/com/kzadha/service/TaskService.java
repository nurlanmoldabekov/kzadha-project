package com.kzadha.service;

import com.kzadha.model.Task;
import com.kzadha.model.enums.TaskStatus;
import com.kzadha.model.json.CreateTaskRequest;

public interface TaskService {
    Task create(CreateTaskRequest request);
    Task setExecutor(Long userId, Long taskId);
    void signTask(Long userId, Long taskId);
    void move(Long taskId, TaskStatus status);
}
