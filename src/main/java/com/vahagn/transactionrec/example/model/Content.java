package com.vahagn.transactionrec.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

/**
 * The type Content.
 */
@Getter
@Setter
@AllArgsConstructor
public class Content {
    private HashSet<Transaction> firstDataSet;
    private HashSet<Transaction> secondDataSet;
}
