package com.startup.infrastructure.exception;

public abstract class ApplicationException extends RuntimeException {

    private String code;
    protected String message;

    public ApplicationException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
