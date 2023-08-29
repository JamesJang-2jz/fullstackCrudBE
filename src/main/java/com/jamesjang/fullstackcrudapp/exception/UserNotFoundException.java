package com.jamesjang.fullstackcrudapp.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("User with id: " + id + " could not be found");
    }

  
}
