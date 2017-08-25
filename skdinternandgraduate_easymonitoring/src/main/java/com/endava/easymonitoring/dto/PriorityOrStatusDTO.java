package com.endava.easymonitoring.dto;

public class PriorityOrStatusDTO {
    private String name;
    private String value;

    public PriorityOrStatusDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public PriorityOrStatusDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
