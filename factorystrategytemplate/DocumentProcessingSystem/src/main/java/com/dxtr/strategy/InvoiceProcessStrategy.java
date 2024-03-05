package com.dxtr.strategy;

import com.dxtr.factory.Document;

/**
 * InvoiceProcessStrategy
 */
public class InvoiceProcessStrategy implements ProcessStrategy {

    @Override
    public void process(Document doc) {
        System.out.println("Processing Invoice with specific Steps");
    }

    
}

