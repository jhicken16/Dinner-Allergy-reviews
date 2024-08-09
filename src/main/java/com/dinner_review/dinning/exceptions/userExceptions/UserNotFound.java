package com.dinner_review.dinning.exceptions.userExceptions;

public class UserNotFound extends RuntimeException {
    private static final Short serialVersionUID = 1;

    public UserNotFound(String message){
        super(message);
    }
}
