package com.kzadha.model;

import com.kzadha.model.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Task {
    private Long id;
    private String description;
    private Long userId;
    private Long executorUserId;
    private Date createDate;
    private Date updateDate;
    private TaskStatus status;
}
