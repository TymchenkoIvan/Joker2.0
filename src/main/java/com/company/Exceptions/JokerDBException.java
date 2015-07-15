package com.company.Exceptions;

/**
 * Will be created If Exception situation will happens when app works with database.
 *
 * Created by tymchenkoivan on 12.07.2015.
 */
public class JokerDBException extends JokerAppException {

    public JokerDBException(){
        super();
    }

    public JokerDBException(String message){
        super(message);
    }
}
