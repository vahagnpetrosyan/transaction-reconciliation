package com.vahagn.transactionrec.example.handler;

import com.vahagn.transactionrec.example.model.Content;

/**
 * The type Transactions handler.
 */
public abstract class TransactionsHandler {
    /**
     * Handle.
     *
     * @param content the content
     */
    public abstract void handle(Content content);
}
