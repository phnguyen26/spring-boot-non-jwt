package com.javaweb.customException;

import java.util.Map;

public class RequiredFieldException extends RuntimeException{
    private String defaultDetailMessage = "This field is required";
    private Map<String, String> errorDetails;
    public RequiredFieldException(String str){
        super(str);
    }

    public String getDefaultDetailMessage() {
        return defaultDetailMessage;
    }


    public Map<String, String> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(Map<String, String> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
