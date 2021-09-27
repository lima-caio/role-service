package com.marvin.roleservice.exception;

/**
 * Exception para quando uma entidade não é encontrada.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
