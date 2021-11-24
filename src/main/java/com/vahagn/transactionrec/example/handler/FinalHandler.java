package com.vahagn.transactionrec.example.handler;

import com.vahagn.transactionrec.example.dto.ReconciliationResult;
import com.vahagn.transactionrec.example.model.Content;

import java.util.ArrayList;

/**
 * The type Final handler.
 */
public class FinalHandler extends TransactionsHandler {
    @Override
    public void handle(Content content) {
        ReconciliationResult.getInstance().setUnMatchedList1(new ArrayList<>(content.getFirstDataSet()));
        ReconciliationResult.getInstance().setUnMatchedList2(new ArrayList<>(content.getSecondDataSet()));
        ReconciliationResult.getInstance().setUnmatchedRecordsFile1(content.getFirstDataSet().size());
        ReconciliationResult.getInstance().setUnmatchedRecordsFile2(content.getSecondDataSet().size());
    }
}
