package com.dinner_review.dinning.exceptions.ValidationExceptions;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class InputErrorObject {

    public InputErrorObject(Map<String, String> message){
        this.message = message;
    }

    @Getter @Setter Map<String, String> message;

}
