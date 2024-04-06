package com.ducph.newtest.dto;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        this("EntityRepresentationModel not found!");
    }

    public NotFoundException(String message) {
        this(message, null);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
