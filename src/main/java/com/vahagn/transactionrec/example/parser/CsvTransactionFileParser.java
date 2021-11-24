package com.vahagn.transactionrec.example.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.vahagn.transactionrec.example.exception.FileException;
import com.vahagn.transactionrec.example.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Csv transaction file parser.
 */
@Service
public class CsvTransactionFileParser implements TransactionFileParser{
    private static final Logger logger = LoggerFactory.getLogger(CsvTransactionFileParser.class);

    @Override
    public List<Transaction> parse(File file) {
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> lines = reader.readAll();
            List<Transaction> transactionList = new ArrayList<>();
            for (int i = 1; i < lines.size(); ++i) {
                String[] line = lines.get(i);
                Transaction transaction = new Transaction();
                transaction.setProfileName(line[0]);
                transaction.setTransactionDate(LocalDateTime.parse(line[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                transaction.setTransactionAmount(Long.parseLong(line[2]));
                transaction.setTransactionNarrative(line[3]);
                transaction.setTransactionDescription(line[4]);
                transaction.setTransactionId(line[5]);
                transaction.setTransactionType(Integer.parseInt(line[6]));
                transaction.setWalletReference(line[7]);

                transactionList.add(transaction);
            }
            return transactionList;

        } catch (IOException | CsvException e) {
            String msg = "Unable to parse the file " + file.getName();
            logger.error(msg);
            throw new FileException(msg, e);
        }
    }
}
