package com.vahagn.transactionrec.example.parser;

import com.vahagn.transactionrec.example.model.Transaction;

import java.io.File;
import java.util.List;

/**
 * The interface Transaction file parser.
 */
public interface TransactionFileParser {
    /**
     * Parse list.
     *
     * @param file the file
     * @return the list
     */
    List<Transaction> parse(File file);
}
