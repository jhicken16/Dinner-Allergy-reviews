package com.dinner_review.dinning.model.RequestBodies;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UpdateReviewRequestBody {

    
    @Size(max = 255, message = "Username to long.")
    @Getter String userName;
    public void setUserName(@Nullable String userName){
        stringNotBlank(userName);
        this.userName = userName;
    }

    @Nullable
    @Max(5)
    @PositiveOrZero(message = "Must be 0 or above.")
    @Getter @Setter Integer peanutScore;

    @Nullable
    @Max(5)
    @PositiveOrZero(message = "Must be 0 or above.")
    @Getter @Setter Integer eggScore;

    @Nullable
    @Max(5)
    @PositiveOrZero(message = "Must be 0 or above.")
    @Getter @Setter Integer dairyScore;

    
    @Size(min = 1, max = 255, message = "Comment to long.")
    @Getter String comments;
    public void setComments(@Nullable String comments){
        stringNotBlank(comments);
        this.comments = comments;
    }

    public void valueToString(){
        System.out.println("Username: " + this.getUserName() + " Peanut score: " + this.getPeanutScore() + " Egg score: " + this.getEggScore() + " Dairy score: " + this.getDairyScore() + " Comments: " + this.getComments());
    }

    private void stringNotBlank(String s){
        if(s != null && s.trim().isEmpty()){
            throw new IllegalArgumentException("you have only used empty space");
        }
    }
}
