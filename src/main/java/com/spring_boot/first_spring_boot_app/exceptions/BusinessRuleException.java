package com.spring_boot.first_spring_boot_app.exceptions;

public class BusinessRuleException extends RuntimeException{
    public BusinessRuleException(String mensaje){
        super(mensaje);
    }
}