package com.dinner_review.dinning.model.DTO;

import java.util.HashMap;

import com.dinner_review.dinning.model.entity.Review;

import lombok.Getter;
import lombok.Setter;

public class ReviewDTO {

    public ReviewDTO(Review i){
        id = i.getId();
        restaurant_id = i.getRestaurant().getId();
        comments = i.getComments();

        if(i.getPeanutScore() > 0){
            this.scores.put("peanutScore", i.getPeanutScore());
        }
        if(i.getEggScore() > 0){
            this.scores.put("eggScore", i.getEggScore());
        }
        if(i.getDairyScore() > 0){
            this.scores.put("dairyScore", i.getDairyScore());
        }
    }

    @Getter @Setter private Long id;

    @Getter @Setter private Long restaurant_id;

    @Getter @Setter private String comments;

    @Getter @Setter private HashMap<String, Integer> scores = new HashMap<String, Integer>();
}
