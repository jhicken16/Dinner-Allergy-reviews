package com.dinner_review.dinning.exceptions.ReviewExceptions;

public class ReviewUnknownException extends RuntimeException{
    private static final Short serialVersionUID = 1;

    public ReviewUnknownException(String message){
        super(message);
    }
}
