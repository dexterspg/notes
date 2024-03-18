package com.example.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EmployeeErrorResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeErrorResponse {

    private int status;
    private String message;
    private long timeStamp;


    
}
