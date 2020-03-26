package com.kzadha.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTask {
    private Long userId;
    private Long taskId;
    private boolean isSigned;
}
