package com.kzadha.service;

import com.kzadha.model.Task;
import com.kzadha.model.enums.TaskStatus;

import java.util.List;

public interface TaskService {
    List<Task> getAll();
    Task create(Task request);
    void setExecutor(Long userId, Long taskId);
    void signTask(Long taskId);
    void move(Long taskId, TaskStatus status);
}
