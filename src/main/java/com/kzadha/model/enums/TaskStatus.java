package com.kzadha.model.enums;

public enum TaskStatus {
    CREATED("Новый"), ON_HOLD("Ждет согласования"), CONFIRMED("Согласован"),
    IN_PROGRESS("Выполняется"), FINISHED("Выполнен");


    private String value;


    public String getValue()
    {
        return this.value;
    }

    private TaskStatus(String value)
    {
        this.value = value;
    }
}
