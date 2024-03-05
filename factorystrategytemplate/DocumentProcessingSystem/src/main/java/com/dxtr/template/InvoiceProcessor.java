package com.dxtr.template;

import com.dxtr.factory.Document;
import com.dxtr.factory.Invoice;
import com.dxtr.strategy.InvoiceProcessStrategy;

/**
 * InvoiceProcessor
 */
public class InvoiceProcessor extends DocumentProcessor{
    

    public InvoiceProcessor() {
        super(new InvoiceProcessStrategy());
    }

    @Override
    public Document createDocument() {
        return new Invoice();
    }

    
}
