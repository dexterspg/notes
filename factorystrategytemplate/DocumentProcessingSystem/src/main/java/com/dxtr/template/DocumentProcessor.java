package com.dxtr.template;

import com.dxtr.factory.Document;
import com.dxtr.strategy.ProcessStrategy;

/**
 * DocumentProcessor
 */
public abstract class DocumentProcessor {

    private ProcessStrategy strategy;

    public DocumentProcessor(ProcessStrategy strategy) {
        this.strategy = strategy;
    }

    //template method
    public void processDocument(Document doc) {
        printHeader();
        strategy.process(doc);
        printFooter();
    }

    private void printHeader() {
        System.out.println("Document processing starts");
    }

    private void printFooter() {
        System.out.println("Document processing ends");
    }

    //factory method
    public abstract Document createDocument();
    
}
