package com.startup.infrastructure.persistence.exception;

import com.startup.infrastructure.exception.ApplicationException;

public class InfrastructureException extends ApplicationException {

    public InfrastructureException(String code, String message) {
        super(code, message);
    }
}
