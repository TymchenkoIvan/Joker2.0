package com.company.exception;

public class JokerDBException extends RuntimeException{

    public JokerDBException() {
    }

    public JokerDBException(Exception ex) {
        super(ex);
    }
}
