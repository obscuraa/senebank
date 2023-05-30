package com.group.senebank.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message, Object id) {
        super(String.format(message, id));
    }
}
