package com.dinner_review.dinning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbInteractionException;
import com.dinner_review.dinning.exceptions.ReviewExceptions.ReviewUnknownException;
import com.dinner_review.dinning.exceptions.ReviewExceptions.ReviewsNotFoundException;
import com.dinner_review.dinning.exceptions.ValidationExceptions.NullReferenceException;
import com.dinner_review.dinning.model.entity.AppUser;
import com.dinner_review.dinning.model.entity.Restaurant;
import com.dinner_review.dinning.model.entity.Review;
import com.dinner_review.dinning.repository.ReviewRepository;

import io.micrometer.common.lang.Nullable;

public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    //Create Review

    /*
     * Other Logic will be added in the layer above this one in order to 
     * Validate Restaurant Id
     * Validate UserName
     */
    public Review createReview(
        Restaurant restaurant,
        AppUser user,
        Integer peanutScore,
        Integer eggScore,
        Integer dairyScore,
        String comments
    ){
        //user input should already be validated before this layer
        try{

            Review newReview = new Review();
            newReview.setRestaurant(restaurant);
            newReview.setUserName(user.getUserName());
            newReview.setPeanutScore(peanutScore);
            newReview.setEggScore(eggScore);
            newReview.setDairyScore(dairyScore);
            newReview.setComments(comments);
            newReview.setAdminAction(false);

            return this.reviewRepository.save(newReview);
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Create_Review_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(Exception e){
            System.out.println("----Exception----Create_Review_Error - " + e.getMessage());
            throw new ReviewUnknownException("Unknown Error");
        } 
    }

    //Read Reviews
        //Review with Restaurant id
    public List<Review> getRestaurantReviews(Restaurant restaurant){

        try{
            Optional<List<Review>> reviews = Optional.ofNullable(this.reviewRepository.findByRestaurant(restaurant));
            if(reviews.isEmpty()){
                //throw reviews not found
                throw new ReviewsNotFoundException("No reviews found for Restaurant.");
            }
            List<Review> restaurantReviews = reviews.get();
            return restaurantReviews;
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Read_Review_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(ReviewsNotFoundException e){
            System.out.println("----Not_Found_Exception----Read_Review_Error - " + e.getMessage());
            throw e;
        }
        catch(Exception e){
            System.out.println("----Exception----Read_Review_Error - " + e.getMessage());
            throw new ReviewUnknownException("Unknown Error");
        } 
    }
    //Update Reviews
    public Review updateReview(
        Long id,
        @Nullable String userName,
        @Nullable Integer peanutScore,
        @Nullable Integer eggScore,
        @Nullable Integer dairyScore,
        @Nullable String comments
    ){
        Review reviewToUpdate;
        try{

            if(userName == null && peanutScore == null && eggScore == null && dairyScore == null && comments == null){
                throw new IllegalArgumentException("Please supply a value to update.");
            }

            Optional<Review> maybeReview = this.reviewRepository.findById(id);
            if(maybeReview.isEmpty()){
                throw new ReviewsNotFoundException("The review you wish to Update has not been found.");
            }

            reviewToUpdate = maybeReview.get();
            if(userName != null){
                reviewToUpdate.setUserName(userName);
            }
            if(peanutScore != null){
                reviewToUpdate.setPeanutScore(peanutScore);
            }
            if(eggScore != null){
                reviewToUpdate.setEggScore(eggScore);
            }
            if(dairyScore != null){
                reviewToUpdate.setDairyScore(dairyScore);
            }
            if(comments != null){
                reviewToUpdate.setComments(userName);
            }
            reviewToUpdate.setAdminAction(false);
            return this.reviewRepository.save(reviewToUpdate);
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Update_Review_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
            System.out.println("----Exception IllegalArgumentException----Update_Review_Error - " + e.getMessage());
            throw new NullReferenceException(e.getMessage(), "name");
        }
        catch(ReviewsNotFoundException e){
            System.out.println("----Not_Found_Exception----Update_Review_Error - " + e.getMessage());
            throw e;
        }
        catch(Exception e){
            System.out.println("----Exception----Update_Review_Error - " + e.getMessage());
            throw new ReviewUnknownException("Unknown Error");
        }
    }
    
    //Delete Reviews
    public Review deleteReview(Long id){

        Review reviewToDelete;
        try{
            Optional<Review> mightBeReview = this.reviewRepository.findById(id);
            if(mightBeReview.isEmpty()){
                throw new ReviewsNotFoundException("Sorry the Review has Not be found.");
            }
            reviewToDelete = mightBeReview.get();
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Read_Review_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(ReviewsNotFoundException e){
            System.out.println("----Not_Found_Exception----Read_Review_Error - " + e.getMessage());
            throw e;
        }

        try{
            this.reviewRepository.delete(reviewToDelete);
            return reviewToDelete;
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Read_Review_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(ReviewsNotFoundException e){
            System.out.println("----Not_Found_Exception----Read_Review_Error - " + e.getMessage());
            throw e;
        }
    }

}
