package com.kzadha.model.json;

import lombok.Data;

@Data
public class CreateTaskRequest {
    private Long userId;
    private Long executorId;
    private Long taskId;
    private String description;
}
