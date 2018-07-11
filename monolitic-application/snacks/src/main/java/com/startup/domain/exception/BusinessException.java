package com.startup.domain.exception;

import com.startup.infrastructure.exception.ApplicationException;

public class BusinessException extends ApplicationException {

    public BusinessException(String code, String message) {
        super(code, message);
    }
}
