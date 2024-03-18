package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * StudentError
 */
@Data
@AllArgsConstructor
public class StudentErrorResponse {

   private int status;
   private String message;
   private long timeStamp;
    
}
