package com.dinner_review.dinning.service.facades;

import java.util.List;

import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbConflict;
import com.dinner_review.dinning.model.entity.AppUser;
import com.dinner_review.dinning.model.entity.Restaurant;
import com.dinner_review.dinning.model.entity.Review;
import com.dinner_review.dinning.repository.RestaurantRepository;
import com.dinner_review.dinning.repository.ReviewRepository;
import com.dinner_review.dinning.repository.UserRepository;
import com.dinner_review.dinning.service.AppUserService;
import com.dinner_review.dinning.service.RestaurantService;
import com.dinner_review.dinning.service.ReviewService;

public class ReviewFacade {
    
    private final RestaurantService restaurantService;
    private final AppUserService appUserService;
    private final ReviewService reviewService;

    public ReviewFacade(
        RestaurantRepository restaurantRepository,
        UserRepository userRepository,
        ReviewRepository reviewRepository
        ){
            this.restaurantService = new RestaurantService(restaurantRepository);
            this.appUserService = new AppUserService(userRepository);
            this.reviewService = new ReviewService(reviewRepository);
    }

    public Review createReviewForRestaurant(
        Long restaurantId,
        String userName,
        Integer peanutScore,
        Integer eggScore,
        Integer dairyScore,
        String comments
        ){
            if(this.restaurantService.hasUserAlreadyMadeReview(restaurantId, userName)){
                throw new DbConflict("Please modify review instead of creating new one.");
            }
            AppUser user = this.appUserService.GetUserByName(userName);
            Restaurant restaurant = this.restaurantService.getRestaurantById(restaurantId);
            return this.reviewService.createReview(restaurant, user, peanutScore, eggScore, dairyScore, comments);

    }

    public List<Review> getReviewsForRestaurants(Long restaurantId){
        Restaurant restaurant = this.restaurantService.getRestaurantById(restaurantId);
        return this.reviewService.getRestaurantReviews(restaurant);
    }


}
