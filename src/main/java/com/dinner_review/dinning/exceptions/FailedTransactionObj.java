package com.dinner_review.dinning.exceptions;

import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class FailedTransactionObj {
    @Getter @Setter private Integer statusCode;
    @Getter @Setter private String message;
    @Getter @Setter private Date timeStamp;
    @Getter @Setter private ArrayList<Long> failUpdates;
}
