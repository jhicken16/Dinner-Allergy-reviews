package com.dinner_review.dinning.exceptions.userExceptions;

public class UserUnknownError extends RuntimeException{
    private static final Short serialVersionUID = 1;

    public UserUnknownError(String message){
        super(message);
    }
}
