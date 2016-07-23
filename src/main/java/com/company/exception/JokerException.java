package com.company.exception;

public abstract class JokerException extends RuntimeException{
    public JokerException(Exception ex) {
        super(ex);
    }

    public JokerException() {
    }
}
