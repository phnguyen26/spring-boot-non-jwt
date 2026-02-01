package com.javaweb.DTO;

import java.util.Map;

public class ErrorDTO {
    private String error;
    private Map<String, String> details;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
