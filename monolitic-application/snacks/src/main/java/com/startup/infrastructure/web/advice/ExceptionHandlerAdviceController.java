package com.startup.infrastructure.web.advice;


import com.startup.domain.exception.BusinessException;
import com.startup.infrastructure.persistence.exception.InfrastructureException;
import com.startup.infrastructure.web.model.ExceptionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionHandlerAdviceController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdviceController.class);

    private static Map<String, HttpStatus> status = new HashMap<>();

    static {
        status.put("NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionModel> handleBusinessException(final BusinessException e) {
        LOG.error(MessageFormat.format("BusinessException: Code: {0}; Message: {1};", e.getCode(), e.getMessage()), e);

        return new ResponseEntity<>(new ExceptionModel(e.getCode(), e.getMessage()), statusCode(e.getCode()));
    }

    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<ExceptionModel> handleInfrastructureException(final InfrastructureException e) {
        LOG.error(MessageFormat.format("InfrastructureException: Code: {0}; Message: {1};", e.getCode(), e.getMessage()), e);

        return new ResponseEntity<>(new ExceptionModel(e.getCode(), e.getMessage()), statusCode(e.getCode()));
    }

    private HttpStatus statusCode(String code) {
        return status.get(code);
    }


}
