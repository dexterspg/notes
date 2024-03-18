package com.example.exceptionhandling;

/**
 * EmployeeNotFoundException
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
