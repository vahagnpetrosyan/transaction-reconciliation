package com.vahagn.transactionrec.example.parser;

import com.vahagn.transactionrec.example.model.Transaction;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;


public class CsvFileTransactionParserTest {

    @Test
    public void parseFileTest() throws URISyntaxException {
        CsvTransactionFileParser csvTransactionFileParser = new CsvTransactionFileParser();

        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("clientmarkofffile20140113.csv")).toURI());
        List<Transaction> transactions = csvTransactionFileParser.parse(file);
        assert(transactions.size() == 306);

        File file1 = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("tutukamarkofffile20140113.csv")).toURI());
        transactions = csvTransactionFileParser.parse(file1);
        assert(transactions.size() == 305);

    }
}
