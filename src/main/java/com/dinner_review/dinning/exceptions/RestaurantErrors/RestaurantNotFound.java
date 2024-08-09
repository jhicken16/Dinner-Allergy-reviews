package com.dinner_review.dinning.exceptions.RestaurantErrors;

public class RestaurantNotFound extends RuntimeException{
    private static final Short serialVersionUID = 1;

    public RestaurantNotFound(String message){
        super(message);
    }
}
