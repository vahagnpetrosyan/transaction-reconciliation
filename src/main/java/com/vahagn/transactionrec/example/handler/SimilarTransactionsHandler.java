package com.vahagn.transactionrec.example.handler;

import com.vahagn.transactionrec.example.dto.ReconciliationResult;
import com.vahagn.transactionrec.example.model.Content;
import com.vahagn.transactionrec.example.model.Transaction;
import lombok.AllArgsConstructor;

import java.util.Iterator;

/**
 * The type Similar transactions handler.
 */
@AllArgsConstructor
public class SimilarTransactionsHandler extends TransactionsHandler{
    private final TransactionsHandler nextHandler;

    @Override
    public void handle(Content content) {
        int similarTransactionsCount = 0;
        for (Iterator<Transaction> it1 = content.getFirstDataSet().iterator(); it1.hasNext();) {
            Transaction t1 = it1.next();
            int maxCommonFields = 0;
            Iterator<Transaction> matching = null;
            for (Iterator<Transaction> it2 = content.getSecondDataSet().iterator(); it2.hasNext();) {
                Transaction t2 = it2.next();

                int commonFieldsCount = t1.countCommonFields(t2);
                if (commonFieldsCount > maxCommonFields) {
                    maxCommonFields = commonFieldsCount;
                    matching = it2;
                }
            }

            // more than 50% common fields
            if (maxCommonFields > 4) {
                similarTransactionsCount++;
                it1.remove();
                matching.remove();
            }
        }
        ReconciliationResult.getInstance().setSimilarRecords(similarTransactionsCount);
        nextHandler.handle(content);
    }
}
