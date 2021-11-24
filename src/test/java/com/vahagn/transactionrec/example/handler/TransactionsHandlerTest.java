package com.vahagn.transactionrec.example.handler;

import com.vahagn.transactionrec.example.dto.ReconciliationResult;
import com.vahagn.transactionrec.example.model.Content;
import com.vahagn.transactionrec.example.model.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class TransactionsHandlerTest {

    @Test
    public void handle() {
        IdenticalTransactionsHandler identicalTransactionsHandler = new IdenticalTransactionsHandler(new SimilarTransactionsHandler(new FinalHandler()));

        identicalTransactionsHandler.handle(getPreData());
        assert(ReconciliationResult.getInstance().getUnmatchedRecordsFile1() == 1);
        assert(ReconciliationResult.getInstance().getUnmatchedRecordsFile2() == 1);


    }

    public static Content getPreData() {
       return new Content(getFirstDataSet(), getSecondDataSet());
    }

    public static Content getPostData() {
        HashSet<Transaction> firstSet = new HashSet<>();

        Transaction t1 = new Transaction();
        t1.setProfileName("Card Campaign");
        t1.setTransactionDate(LocalDateTime.parse("2014-01-11 22:27:44", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        t1.setTransactionAmount(-20000L);
        t1.setTransactionNarrative("*MOLEPS ATM25             MOLEPOLOLE    BW");
        t1.setTransactionDescription("DEDUCT");
        t1.setTransactionId("0584011808649512");
        t1.setTransactionType(1);
        t1.setWalletReference("P_NzI2ODY2ODlfMTM4MjcwMTU2NS45MzA5");
        firstSet.add(t1);

        HashSet<Transaction> secondSet = new HashSet<>();

        Transaction t2 = new Transaction();
        t1.setProfileName("Card Campaign");
        t1.setTransactionDate(LocalDateTime.parse("2014-01-11 22:27:44", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        t1.setTransactionAmount(-20000L);
        t1.setTransactionNarrative("*MOLEPS ATM25             MOLEPOLOLE    BW");
        t1.setTransactionDescription("DEDUCT");
        t1.setTransactionId("0584011808649511");
        t1.setTransactionType(1);
        t1.setWalletReference("P_NzI2ODY2ODlfMTM4MjcwMTU2NS45MzA5");
        secondSet.add(t2);

        return new Content(firstSet, secondSet);
    }

    public static HashSet<Transaction> getFirstDataSet() {
            HashSet<Transaction> firstSet = new HashSet<>();

            Transaction t1 = new Transaction();
            t1.setProfileName("Card Campaign");
            t1.setTransactionDate(LocalDateTime.parse("2014-01-11 22:27:44", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            t1.setTransactionAmount(-20000L);
            t1.setTransactionNarrative("*MOLEPS ATM25             MOLEPOLOLE    BW");
            t1.setTransactionDescription("DEDUCT");
            t1.setTransactionId("0584011808649512");
            t1.setTransactionType(1);
            t1.setWalletReference("P_NzI2ODY2ODlfMTM4MjcwMTU2NS45MzA5");

            Transaction t2 = new Transaction();
            t2.setProfileName("Card Campaign");
            t2.setTransactionDate(LocalDateTime.parse("2014-01-11 22:39:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            t2.setTransactionAmount(-10000L);
            t2.setTransactionNarrative("*MOGODITSHANE2            MOGODITHSANE  BW");
            t2.setTransactionDescription("DEDUCT");
            t2.setTransactionId("0584011815513406");
            t2.setTransactionType(1);
            t2.setWalletReference("P_NzI1MjA1NjZfMTM3ODczODI3Mi4wNzY5");

            Transaction t3 = new Transaction();
            t3.setProfileName("Card Campaign");
            t3.setTransactionDate(LocalDateTime.parse("2014-01-11 23:28:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            t3.setTransactionAmount(-5000L);
            t3.setTransactionNarrative("CAPITAL BANK              MOGODITSHANE  BW");
            t3.setTransactionDescription("DEDUCT");
            t3.setTransactionId("0464011844938429");
            t3.setTransactionType(1);
            t3.setWalletReference("P_NzI0NjE1NzhfMTM4NzE4ODExOC43NTYy");

            Transaction t4 = new Transaction();
            t4.setProfileName("Card Campaignn");
            t4.setTransactionDate(LocalDateTime.parse("2015-01-12 02:18:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            t4.setTransactionAmount(-100000L);
            t4.setTransactionNarrative("PALAPYEE BRANCH ATM        PALAPYE       BW");
            t4.setTransactionDescription("DEDUCT");
            t4.setTransactionId("0464011802918801");
            t4.setTransactionType(1);

            firstSet.add(t1);
            firstSet.add(t2);
            firstSet.add(t3);
            firstSet.add(t4);

            return firstSet;
    }

    public static HashSet<Transaction> getSecondDataSet () {
        HashSet<Transaction> secondSet = new HashSet<>();

        Transaction t1 = new Transaction();
        t1.setProfileName("Card Campaign");
        t1.setTransactionDate(LocalDateTime.parse("2014-01-11 22:27:44", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        t1.setTransactionAmount(-20000L);
        t1.setTransactionNarrative("*MOLEPS ATM25             MOLEPOLOLE    BW");
        t1.setTransactionDescription("DEDUCT");
        t1.setTransactionId("0584011808649511");
        t1.setTransactionType(1);
        t1.setWalletReference("P_NzI2ODY2ODlfMTM4MjcwMTU2NS45MzA5");

        Transaction t2 = new Transaction();
        t2.setProfileName("Card Campaign");
        t2.setTransactionDate(LocalDateTime.parse("2014-01-11 22:39:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        t2.setTransactionAmount(-10000L);
        t2.setTransactionNarrative("*MOGODITSHANE2            MOGODITHSANE  BW");
        t2.setTransactionDescription("DEDUCT");
        t2.setTransactionId("0584011815513406");
        t2.setTransactionType(1);
        t2.setWalletReference("P_NzI1MjA1NjZfMTM3ODczODI3Mi4wNzY5");

        Transaction t3 = new Transaction();
        t3.setProfileName("Card Campaign");
        t3.setTransactionDate(LocalDateTime.parse("2014-01-11 23:28:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        t3.setTransactionAmount(-5000L);
        t3.setTransactionNarrative("CAPITAL BANK              MOGODITSHANE  BW");
        t3.setTransactionDescription("DEDUCT");
        t3.setTransactionId("0464011844938429");
        t3.setTransactionType(1);
        t3.setWalletReference("P_NzI0NjE1NzhfMTM4NzE4ODExOC43NTYy");

        Transaction t4 = new Transaction();
        t4.setProfileName("Card Campaign");
        t4.setTransactionDate(LocalDateTime.parse("2014-01-12 02:18:11", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        t4.setTransactionAmount(-10000L);
        t4.setTransactionNarrative("PALAPYE BRANCH ATM        PALAPYE       BW");
        t4.setTransactionDescription("DEDUCT");
        t4.setTransactionId("0464011802918802");
        t4.setTransactionType(1);

        secondSet.add(t1);
        secondSet.add(t2);
        secondSet.add(t3);
        secondSet.add(t4);

        return secondSet;

    }
}
/*
ProfileName,TransactionDate,TransactionAmount,TransactionNarrative,TransactionDescription,TransactionID,TransactionType,WalletReference
Card Campaign,2014-01-11 22:27:44,-20000,*MOLEPS ATM25             MOLEPOLOLE    BW,DEDUCT,0584011808649511,1,P_NzI2ODY2ODlfMTM4MjcwMTU2NS45MzA5,
Card Campaign,2014-01-11 22:39:11,-10000,*MOGODITSHANE2            MOGODITHSANE  BW,DEDUCT,0584011815513406,1,P_NzI1MjA1NjZfMTM3ODczODI3Mi4wNzY5,
Card Campaign,2014-01-11 23:28:11,-5000,CAPITAL BANK              MOGODITSHANE  BW,DEDUCT,0464011844938429,1,P_NzI0NjE1NzhfMTM4NzE4ODExOC43NTYy,
Card Campaign,2014-01-12 02:18:11,-10000,PALAPYE BRANCH ATM        PALAPYE       BW,DEDUCT,0464011802918801,1,P_NzI5OTQ5NDRfMTM4NDE2MjA2Ny45NjUy,
transaction.setTransactionDate(LocalDateTime.parse(line[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
 */