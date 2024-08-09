package com.dinner_review.dinning.exceptions.ReviewExceptions;

public class ReviewsNotFoundException extends RuntimeException{
    private static final Short serialVersionUID = 1;

    public ReviewsNotFoundException(String message){
        super(message);
    }
}
