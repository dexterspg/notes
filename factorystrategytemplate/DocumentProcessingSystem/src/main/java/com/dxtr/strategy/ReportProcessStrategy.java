package com.dxtr.strategy;

import com.dxtr.factory.Document;

/**
 * ReportProcessStrategy
 */
public class ReportProcessStrategy implements ProcessStrategy{

    @Override
    public void process(Document doc) {
       System.out.println("Processing Report with specific steps"); 
    }

    
}
