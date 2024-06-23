package com.example.demo.exception;


public class UserFoundException extends RuntimeException{
    public UserFoundException(String errorMessage){
        super(errorMessage);
    }
}