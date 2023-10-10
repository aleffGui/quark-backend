package com.guilherme.quarkapi.enums;

public enum TaskPriority {

    LOW("Baixa"),
    MEDIUM("Média"),
    HIGH("Alta");

    private String value;

    TaskPriority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
