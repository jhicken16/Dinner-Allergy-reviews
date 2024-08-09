package com.dinner_review.dinning.service;

import com.dinner_review.dinning.repository.RestaurantRepository;
import com.dinner_review.dinning.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.TransactionException;
import org.springframework.dao.DataAccessException;

import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbInteractionException;
import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbTransactionException;
import com.dinner_review.dinning.exceptions.RestaurantErrors.RestaurantNotFound;
import com.dinner_review.dinning.exceptions.ReviewExceptions.ReviewsNotFoundException;
import com.dinner_review.dinning.exceptions.ValidationExceptions.NullReferenceException;
import com.dinner_review.dinning.exceptions.userExceptions.UserNotFound;
import com.dinner_review.dinning.exceptions.userExceptions.UserUnknownError;
import com.dinner_review.dinning.model.DTO.ReviewDTO;
import com.dinner_review.dinning.model.entity.Restaurant;
import com.dinner_review.dinning.model.entity.Review;

public class AdminService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository){
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public ArrayList<ReviewDTO> getReviewsWithFalseAdminVue(){
        
        try{
            List<Review> uncheckedReviews = this.reviewRepository.findByAdminActionFalse();
            if(uncheckedReviews.size() == 0){
                throw new ReviewsNotFoundException("There are pending admin action.");
            }

            ArrayList<ReviewDTO> dto = new ArrayList<ReviewDTO>();
            for(int x = 0; x < uncheckedReviews.size(); x++){
                dto.add(new ReviewDTO(uncheckedReviews.get(x)));
            }

            return dto;
        }
        catch(ReviewsNotFoundException ex){
            System.out.println("----Exception Restaurants Not Found----Get_Review_With_False_Admin_Vue - " + ex.getMessage());
            throw ex;
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Get_Review_With_False_Admin_Vue - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(Exception e){
                System.out.println("----Exception----Get_Review_With_False_Admin_Vue - " + e.getMessage());
                throw new UserUnknownError("Unknown Error.");
        }

    }

    public Iterable<Restaurant> updateRestaurantScores(ArrayList<Long> approvedReviews){

        List<Restaurant> restaurantsWithNewApprovedReviews;
        try{
            this.reviewRepository.updateAdminAction(approvedReviews);

            Optional<List<Restaurant>> restaurantsToUpdate = this.restaurantRepository.findRestaurantByReviewId(approvedReviews);

            if(restaurantsToUpdate.isEmpty()){
                throw new RestaurantNotFound("Could Not find Restaurants you wanted to update.");
            }
            
            // Update Restaurants Scores Here!
            restaurantsWithNewApprovedReviews =  restaurantsToUpdate.get();
        }
        catch(RestaurantNotFound ex){
            System.out.println("----Exception Restaurants Not Found----Update_Admin_Action_Error - " + ex.getMessage());
            throw ex;
        }
        catch(TransactionException ex){
            System.out.println("----Exception Restaurants Not Found----Get_Restaurants_By_Review_Id_Error - " + ex.getMessage());
            throw new DbTransactionException("Sorry the Update did not complete", approvedReviews);
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Update_Restaurant_Score_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
                System.out.println("----Exception IllegalArgumentException----Update_Restaurant_Score_Error - " + e.getMessage());
                throw new NullReferenceException(e.getMessage(), "approved review List");
        }
        catch(Exception e){
                System.out.println("----Exception----Update_Restaurant_Score_Error - " + e.getMessage());
                throw new UserUnknownError("Unknown Error.");
        }


        for(int x = 0; x < restaurantsWithNewApprovedReviews.size(); x++){

            float peanutAccumulator = 0;
            float eggAccumulator = 0;
            float dairyAccumulator = 0;

            Iterator<Review> it = restaurantsWithNewApprovedReviews.get(x).getReviews().iterator();
            while(it.hasNext()){
                Review r = it.next();
                peanutAccumulator += r.getPeanutScore();
                eggAccumulator += r.getEggScore();
                dairyAccumulator += r.getDairyScore();
            }
            
            restaurantsWithNewApprovedReviews.get(x).setPeanutScore(peanutAccumulator / restaurantsWithNewApprovedReviews.get(x).getReviews().size());
            restaurantsWithNewApprovedReviews.get(x).setEggScore(eggAccumulator / restaurantsWithNewApprovedReviews.get(x).getReviews().size());
            restaurantsWithNewApprovedReviews.get(x).setDairyScore(dairyAccumulator / restaurantsWithNewApprovedReviews.get(x).getReviews().size());
        }

        return this.restaurantRepository.saveAll(restaurantsWithNewApprovedReviews);
    }

}
