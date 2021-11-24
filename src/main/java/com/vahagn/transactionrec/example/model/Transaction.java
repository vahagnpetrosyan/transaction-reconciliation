package com.vahagn.transactionrec.example.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

/**
 * The type Transaction.
 */
@Data
public class Transaction {
    private String profileName;
    private LocalDateTime transactionDate;
    private Long transactionAmount;
    private String transactionNarrative;
    private String transactionDescription;
    private String transactionId;
    private Integer transactionType;
    private String walletReference;

    /**
     * Count common fields int.
     *
     * @param other the other
     * @return the int
     */
    public int countCommonFields(Transaction other) {
        Function<Boolean, Integer> toInt = b -> b ? 1 : 0;
        return toInt.apply(profileName.equals(other.profileName)) +
                toInt.apply(Objects.equals(transactionDate, other.getTransactionDate())) +
                toInt.apply(Objects.equals(transactionAmount, other.getTransactionAmount())) +
                toInt.apply(Objects.equals(transactionNarrative, other.getTransactionNarrative())) +
                toInt.apply(Objects.equals(transactionDescription, other.getTransactionDescription())) +
                toInt.apply(Objects.equals(transactionId, other.getTransactionId())) +
                toInt.apply(Objects.equals(transactionType, other.getTransactionType())) +
                toInt.apply(Objects.equals(walletReference, other.getWalletReference()));

    }
}

//1) Case insensitive
//2) Company Name Coperation/Co.
// 3) ABC / A  B C