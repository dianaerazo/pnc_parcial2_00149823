package com.example.parcial2.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
       super(message);
    }
}