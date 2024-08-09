package com.dinner_review.dinning.exceptions.RestaurantErrors;

public class RestaurantFailedToCreate extends RuntimeException{
    private static final Short serialVersionUID = 1;

    public RestaurantFailedToCreate(String message){
        super(message);
    }
}
