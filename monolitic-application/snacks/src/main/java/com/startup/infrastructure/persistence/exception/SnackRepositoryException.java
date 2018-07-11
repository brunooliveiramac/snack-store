package com.startup.infrastructure.persistence.exception;

public class SnackRepositoryException extends InfrastructureException {

    public SnackRepositoryException(String code, String msg) {
        super(code, msg);
    }
}
