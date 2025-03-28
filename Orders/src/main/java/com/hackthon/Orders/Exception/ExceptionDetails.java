package com.hackthon.Orders.Exception;

public class ExceptionDetails extends RuntimeException {
    private final String message;

    public ExceptionDetails(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
