package com.finance.app.exceptions;


import com.finance.app.config.APIErrorCodeConfig;
import com.finance.app.config.APPValidationConfig;
import com.finance.app.model.error.APIErrorModel;
import com.finance.app.model.error.APIRequestErrorModel;
import com.finance.app.model.error.ErrorSeverityLevelCodeType;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This is the custom exception handler class
 */
@ControllerAdvice
@RestController
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        UUID uuid = UUID.randomUUID();
        log.error(ex.getMessage());
        final APIRequestErrorModel error = new APIRequestErrorModel(errors, APIErrorCodeConfig.INVALID_INPUT, ErrorSeverityLevelCodeType.ERROR,uuid);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(MaturityCheckException.class)
    @ResponseBody
    public ResponseEntity<Object> handleUserException(MaturityCheckException ex) {
        UUID uuid = UUID.randomUUID();
        log.error(String.valueOf(ex.getMessage()));
        HttpStatus status = ex.getStatus() == null ? HttpStatus.BAD_REQUEST : ex.getStatus();
        final APIErrorModel error = new APIErrorModel(ex.getMessage(), APIErrorCodeConfig.INVALID_INPUT, ErrorSeverityLevelCodeType.ERROR,uuid);
        return new ResponseEntity<>(error, status);
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        UUID uuid = UUID.randomUUID();
        log.error(String.valueOf(ex.getMessage()));
        final APIErrorModel error = new APIErrorModel(ex.getMessage(), APIErrorCodeConfig.INTERNAL_ERROR, ErrorSeverityLevelCodeType.ERROR,uuid);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IllegalArgumentException.class, InvalidDataAccessApiUsageException.class})
    @ResponseBody
    public ResponseEntity<Object> handleArgumentException(Exception ex) {
        UUID uuid = UUID.randomUUID();
        log.error(String.valueOf(ex.getMessage()));
        final APIErrorModel error = new APIErrorModel(ex.getMessage(), APIErrorCodeConfig.INVALID_INPUT, ErrorSeverityLevelCodeType.ERROR,uuid);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        UUID uuid = UUID.randomUUID();
        log.error(String.valueOf(ex.getMessage()));
        String message = ex.getMessage();
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            message = APPValidationConfig.DB_CONSTRAINT_VIOLATED;
        }

        final APIErrorModel error = new APIErrorModel(message, APIErrorCodeConfig.DB_ERROR, ErrorSeverityLevelCodeType.ERROR,uuid);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        UUID uuid = UUID.randomUUID();
        log.error(String.valueOf(ex.getMessage()));
        final APIErrorModel error = new APIErrorModel(ex.getMessage(), APIErrorCodeConfig.DB_ERROR, ErrorSeverityLevelCodeType.ERROR,uuid);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}