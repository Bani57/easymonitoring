package com.endava.easymonitoring.model;

public class Priority {
    private String color;
    private String description;
    private String name;

    public Priority(String color, String description, String name) {
        this.color = color;
        this.description = description;
        this.name = name;
    }

    public Priority() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
