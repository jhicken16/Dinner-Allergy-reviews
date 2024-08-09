package com.dinner_review.dinning.exceptions.DatabaseExceptions;

public class DbConflict extends RuntimeException {
    private static final Short serialVersionUID = 1;

    public DbConflict(String message){
        super(message);
    }
}
