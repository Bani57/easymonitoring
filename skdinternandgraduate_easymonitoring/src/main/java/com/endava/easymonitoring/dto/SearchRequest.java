package com.endava.easymonitoring.dto;

public class SearchRequest {
    private String value;

    public SearchRequest(String value) {
        this.value = value;
    }

    public SearchRequest() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
