package com.project.myshop.util.customexception;

public class SignUpFieldNotValidationException extends RuntimeException {

    public SignUpFieldNotValidationException(String message) {
        super(message);
    }
}
