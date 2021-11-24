package com.vahagn.transactionrec.example.service;

import com.vahagn.transactionrec.example.controller.AppController;
import com.vahagn.transactionrec.example.dto.ReconciliationResult;
import com.vahagn.transactionrec.example.handler.TransactionsHandler;
import com.vahagn.transactionrec.example.model.Content;
import com.vahagn.transactionrec.example.model.Transaction;
import com.vahagn.transactionrec.example.parser.TransactionFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;

/**
 * The type Reconciliation service.
 */
@Service
public class ReconciliationService {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);


    @Autowired
    private FileService fileService;

    @Autowired
    private TransactionFileParser fileParser;

    @Autowired
    private TransactionsHandler transactionsHandler;

    /**
     * Reconcile.
     *
     * @param fileName1 the file name 1
     * @param fileName2 the file name 2
     */
    public void reconcile(String fileName1, String fileName2) {
        logger.debug("Reconciliation process is requested to run with files '{}' and '{}'", fileName1, fileName2);
        String errorMessage = "%s is not a supported file.";
        Assert.isTrue(fileName1 != null && fileName1.toLowerCase().endsWith(".csv"), String.format(errorMessage, fileName1));
        Assert.isTrue(fileName2 != null && fileName2.toLowerCase().endsWith(".csv"), String.format(errorMessage, fileName2));

        //storageService.load(file1Name).toFile()
        List<Transaction> firstTransactionList = fileParser.parse(fileService.load(fileName1).toFile());
        List<Transaction> secondTransactionList = fileParser.parse(fileService.load(fileName2).toFile());

        ReconciliationResult.getInstance().setTotalRecordsFile1(firstTransactionList.size());
        ReconciliationResult.getInstance().setTotalRecordsFile2(secondTransactionList.size());
        Content content = new Content(new HashSet<>(firstTransactionList), new HashSet<>(secondTransactionList));
        transactionsHandler.handle(content);

    }
}
