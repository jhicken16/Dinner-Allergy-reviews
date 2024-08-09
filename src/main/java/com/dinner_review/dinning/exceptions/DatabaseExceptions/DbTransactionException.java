package com.dinner_review.dinning.exceptions.DatabaseExceptions;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class DbTransactionException extends RuntimeException{
    private static final Short serialVersionUID = 1;

    public DbTransactionException(String message, ArrayList<Long> failedTransactions){
        super(message);
        this.failedTransaction = failedTransactions;
    }

    @Getter @Setter private ArrayList<Long> failedTransaction;

}
