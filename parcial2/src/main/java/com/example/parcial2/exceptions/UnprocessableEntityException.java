package com.example.parcial2.exceptions;

public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(String message){
        super(message);
    }
}
