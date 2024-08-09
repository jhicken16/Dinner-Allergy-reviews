package com.dinner_review.dinning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinner_review.dinning.model.DTO.ReviewDTO;
import com.dinner_review.dinning.model.RequestBodies.CreateReviewRequestBody;
import com.dinner_review.dinning.model.RequestBodies.IdInRequestBody;
import com.dinner_review.dinning.model.RequestBodies.UpdateReviewRequestBody;
import com.dinner_review.dinning.model.entity.Review;
import com.dinner_review.dinning.repository.RestaurantRepository;
import com.dinner_review.dinning.repository.ReviewRepository;
import com.dinner_review.dinning.repository.UserRepository;
import com.dinner_review.dinning.service.ReviewService;
import com.dinner_review.dinning.service.facades.ReviewFacade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;



import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/Review")
@Validated
public class ReviewController {

    private final ReviewFacade reviewFacade;
    private final ReviewService reviewService;

    public ReviewController(
        RestaurantRepository restaurantRepository,
        UserRepository userRepository,
        ReviewRepository reviewRepository
        ){
            this.reviewFacade = new ReviewFacade(restaurantRepository, userRepository, reviewRepository);
            this.reviewService = new ReviewService(reviewRepository);
    }

    @GetMapping("/{restaurantId}")
    public List<Review> getReviewById(@PathVariable("restaurantId") @Positive(message = "must be psssotive") Long restaurantId) {

        return this.reviewFacade.getReviewsForRestaurants(restaurantId);

    }

    @PostMapping("/{userName}")
    public ReviewDTO createReviewForRestaurant(
        @PathVariable @NotBlank String userName,
        @Valid @RequestBody CreateReviewRequestBody newReview
        ) {
        Review review = this.reviewFacade.createReviewForRestaurant( newReview.getRestaurantId(),
                                                            userName,
                                                            newReview.getPeanutScore(),
                                                            newReview.getEggScore(),
                                                            newReview.getDairyScore(),
                                                            newReview.getComments());
        ReviewDTO dto = new ReviewDTO(review);

        return dto;
    }

    @PutMapping("/{id}")
    public Review updateReview(
        @PathVariable Long id,
        @Valid @RequestBody UpdateReviewRequestBody update
        ) {
            System.out.println("BANG");
        update.valueToString();
        return this.reviewService.updateReview(id, update.getUserName(), update.getPeanutScore(), update.getEggScore(), update.getDairyScore(), update.getComments());
    }

    @DeleteMapping("/Delete")
    public Review deleteUserReview(@Valid @RequestBody IdInRequestBody id){
        return this.reviewService.deleteReview(id.getId());
    }
    
    
}
