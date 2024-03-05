package com.dxtr.template;

import com.dxtr.factory.Document;
import com.dxtr.factory.Report;
import com.dxtr.strategy.ReportProcessStrategy;

/**
 * ReportProcessor
 */
public class ReportProcessor extends DocumentProcessor {

    public ReportProcessor() {
        super(new ReportProcessStrategy());
        // TODO Auto-generated constructor stub
    }

    @Override
    public Document createDocument() {
        return new Report();
    }

}
