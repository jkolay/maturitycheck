package com.finance.app.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Interface for Recipe Exception
 */
public interface CustomAPIException {
    HttpStatus getStatus();
}