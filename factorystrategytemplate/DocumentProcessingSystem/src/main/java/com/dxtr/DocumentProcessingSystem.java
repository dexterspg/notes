package com.dxtr;

import com.dxtr.factory.Document;
import com.dxtr.template.DocumentProcessor;
import com.dxtr.template.InvoiceProcessor;
import com.dxtr.template.ReportProcessor;

public class DocumentProcessingSystem {
    public static void main(String[] args) {
        DocumentProcessor reportProcessor = new ReportProcessor();
        Document report = reportProcessor.createDocument();
        reportProcessor.processDocument(report);

        DocumentProcessor invoiceProcessor = new InvoiceProcessor();
        Document invoice = invoiceProcessor.createDocument();
        invoiceProcessor.processDocument(invoice);
    }
}

