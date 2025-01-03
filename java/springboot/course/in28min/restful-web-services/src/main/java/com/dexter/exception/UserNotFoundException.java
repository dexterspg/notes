package com.dexter.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {  //Controller Advice supersedes the return response

    public UserNotFoundException(String message){
        super(message);
    }
    
    
}
