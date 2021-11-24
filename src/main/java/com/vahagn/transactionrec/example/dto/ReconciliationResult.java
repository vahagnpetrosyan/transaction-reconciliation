package com.vahagn.transactionrec.example.dto;

import com.vahagn.transactionrec.example.model.Transaction;
import lombok.Data;

import java.util.List;

/**
 * The type Reconciliation result.
 */
@Data
public class ReconciliationResult {
    private static class ReconciliationResultHolder {
        private static final ReconciliationResult INSTANCE = new ReconciliationResult();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ReconciliationResult getInstance() {
        return ReconciliationResultHolder.INSTANCE;
    }

    private int matchingRecords;
    private int totalRecordsFile1;
    private int totalRecordsFile2;
    private int similarRecords;
    private int unmatchedRecordsFile1;
    private int unmatchedRecordsFile2;
    private List<Transaction> unMatchedList1;
    private List<Transaction> unMatchedList2;
}
