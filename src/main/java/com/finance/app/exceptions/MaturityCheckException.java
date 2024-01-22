package com.finance.app.exceptions;

import org.springframework.http.HttpStatus;

public class MaturityCheckException extends  RuntimeException implements CustomAPIException {

    private HttpStatus status = HttpStatus.NOT_FOUND;

    public MaturityCheckException() {

        super();
    }

    public MaturityCheckException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public MaturityCheckException(String message) {

        super(message);
    }

    public MaturityCheckException(String message, Throwable cause) {

        super(message, cause);
    }

    public MaturityCheckException(Throwable cause) {

        super(cause);
    }

    protected MaturityCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getStatus() {

        return status;
    }
}

