package com.dinner_review.dinning.model.DTO;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.dinner_review.dinning.model.entity.Restaurant;
import com.dinner_review.dinning.model.entity.Review;

import lombok.Getter;
import lombok.Setter;

public class RestaurantDTO {

    public RestaurantDTO(Restaurant r){
        this.id = r.getId();
        this.name = r.getName();
        if(r.getPeanutScore() > 0){
            this.scores.put("peanutScore", new DecimalFormat("0.00").format(r.getPeanutScore()) );
        }
        if(r.getEggScore() > 0){
            this.scores.put("eggScore", new DecimalFormat("0.00").format(r.getEggScore()));
        }
        if(r.getDairyScore() > 0){
            this.scores.put("dairyScore", new DecimalFormat("0.00").format(r.getDairyScore()));
        }

        Iterator<Review> i = r.getReviews().iterator();
        while(i.hasNext()){
            this.reviews.add(new ReviewDTO(i.next()));
        }
    }

    @Getter @Setter private Long id;

    @Getter @Setter private Set<ReviewDTO> reviews = new HashSet<>();

    @Getter @Setter private String name;

    @Getter @Setter private HashMap<String, String> scores = new HashMap<String, String>();
}
