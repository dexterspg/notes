package com.dxtr.factory;

/**
 * Report
 */
public class Invoice extends Document {

    @Override
    void printDocument() {
         System.out.println("Printing Invoice");
    }

    
}
