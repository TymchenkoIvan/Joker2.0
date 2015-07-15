package com.company.Exceptions;

/**
 * All Exception situation that we can forecast.
 *
 * Created by tymchenkoivan on 12.07.2015.
 */
public class JokerAppException extends Exception {

    public JokerAppException(){
        super();
    }

    public JokerAppException(String message){
        super(message);
    }
}
