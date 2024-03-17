
package com.example.exception;

/**
 * StudentExceptionHandler
 */
public class StudentNotFoundException  extends RuntimeException{

    public StudentNotFoundException(String message){
        super(message);
    }


}

