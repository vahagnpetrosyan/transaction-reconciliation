package com.vahagn.transactionrec.example.handler;

import com.vahagn.transactionrec.example.dto.ReconciliationResult;
import com.vahagn.transactionrec.example.model.Content;
import com.vahagn.transactionrec.example.model.Transaction;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Identical transactions handler.
 */
@AllArgsConstructor
public class IdenticalTransactionsHandler extends TransactionsHandler{
    private final TransactionsHandler nextHandler;

    @Override
    public void handle(Content content) {
        ReconciliationResult result = ReconciliationResult.getInstance();
        int firstDataSetSize = content.getFirstDataSet().size();

        Set<Transaction> firstSetCopy = new HashSet<>(content.getFirstDataSet());
        content.getFirstDataSet().removeAll(content.getSecondDataSet());
        content.getSecondDataSet().removeAll(firstSetCopy);
        result.setMatchingRecords(firstDataSetSize - content.getFirstDataSet().size());

        nextHandler.handle(content);
    }
}
