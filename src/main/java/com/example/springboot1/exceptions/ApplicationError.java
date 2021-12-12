package com.example.springboot1.exceptions;

public class ApplicationError {

    private int statusCode;
    private String Message;

    public ApplicationError(int statusCode, String message) {
        this.statusCode = statusCode;
        Message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
