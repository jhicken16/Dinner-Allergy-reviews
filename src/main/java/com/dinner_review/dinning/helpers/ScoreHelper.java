package com.dinner_review.dinning.helpers;

public class ScoreHelper {

    public static Boolean validScore(Integer score){
        if(score < 0 || score > 5){
            return false;
        }
        return true;
    }
}
