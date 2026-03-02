package com.spring_boot.first_spring_boot_app.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
