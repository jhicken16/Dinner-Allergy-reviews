package com.dinner_review.dinning.exceptions.ValidationExceptions;

import lombok.Getter;
import lombok.Setter;

public class NullReferenceException extends RuntimeException{
    private static final Short serialVersionUID = 1;

    @Getter @Setter private String nullAssignedTo;

    public NullReferenceException(String message, String nullAssignedTo){
        super(message);
        this.nullAssignedTo = nullAssignedTo;
    }
}
