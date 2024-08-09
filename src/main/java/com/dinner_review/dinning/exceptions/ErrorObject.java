package com.dinner_review.dinning.exceptions;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class ErrorObject {
    @Getter @Setter private Integer statusCode;
    @Getter @Setter private String message;
    @Getter @Setter private Date timeStamp;
}
