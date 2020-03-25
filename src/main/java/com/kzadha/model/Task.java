package com.kzadha.model;

import lombok.Data;

@Data
public class Task {
    private String description;
    private User initiator;
    private User executor;
}
