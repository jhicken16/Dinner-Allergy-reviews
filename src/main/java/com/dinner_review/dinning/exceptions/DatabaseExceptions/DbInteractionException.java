package com.dinner_review.dinning.exceptions.DatabaseExceptions;

public class DbInteractionException extends RuntimeException{
    private static final Short serialVersionUID = 1;
    
    public DbInteractionException(String message){
        super(message);
    }
}
