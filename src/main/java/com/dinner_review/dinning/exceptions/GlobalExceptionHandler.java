package com.dinner_review.dinning.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbConflict;
import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbInteractionException;
import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbTransactionException;
import com.dinner_review.dinning.exceptions.RestaurantErrors.RestaurantFailedToCreate;
import com.dinner_review.dinning.exceptions.RestaurantErrors.RestaurantNotFound;
import com.dinner_review.dinning.exceptions.ReviewExceptions.ReviewUnknownException;
import com.dinner_review.dinning.exceptions.ReviewExceptions.ReviewsNotFoundException;
import com.dinner_review.dinning.exceptions.ValidationExceptions.InputErrorObject;
import com.dinner_review.dinning.exceptions.ValidationExceptions.NullReferenceException;
import com.dinner_review.dinning.exceptions.userExceptions.UserNotFound;
import com.dinner_review.dinning.exceptions.userExceptions.UserUnknownError;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantNotFound.class)
    public ResponseEntity<ErrorObject> handleRestaurantNotFoundException(RestaurantNotFound ex, WebRequest request){
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RestaurantFailedToCreate.class)
    public ResponseEntity<ErrorObject> handleRestaurantFailedToCreate(RestaurantFailedToCreate ex, WebRequest request){
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(DbInteractionException.class)
    public ResponseEntity<ErrorObject> HandleDbIterationException(DbInteractionException ex, WebRequest request){
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullReferenceException.class)
    public ResponseEntity<ErrorObject> HandleNullReferenceException(NullReferenceException ex, WebRequest request){
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObj.setMessage(ex.getMessage() + "Null Field = " + ex.getNullAssignedTo());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserUnknownError.class)
    public ResponseEntity<ErrorObject> handleUnknownException(UserUnknownError ex, WebRequest request){
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorObject> handleUserNotFound(UserNotFound ex, WebRequest request){
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(DbConflict.class)
    public ResponseEntity<ErrorObject> handleDbConflict(DbConflict ex, WebRequest request){
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.CONFLICT.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(ReviewUnknownException.class)
    public ResponseEntity<ErrorObject> handleReviewUnknownException(ReviewUnknownException ex, WebRequest request){
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ReviewsNotFoundException.class)
    public ResponseEntity<ErrorObject> handleReviewsNotFoundException(ReviewsNotFoundException ex, WebRequest request){
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<InputErrorObject> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
     
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<InputErrorObject>(new InputErrorObject(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorObject> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){

        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DbTransactionException.class)
    public ResponseEntity<FailedTransactionObj> handleDbTransactionError(DbTransactionException ex, WebRequest request){
        FailedTransactionObj errObj = new FailedTransactionObj();
        errObj.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
        errObj.setMessage(ex.getMessage());
        errObj.setTimeStamp(new Date());
        errObj.setFailUpdates(ex.getFailedTransaction());

        return new ResponseEntity<FailedTransactionObj>(errObj, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorObject> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){

        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.BAD_REQUEST);
    }
}
