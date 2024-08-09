package com.dinner_review.dinning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinner_review.dinning.model.DTO.ReviewDTO;
import com.dinner_review.dinning.model.RequestBodies.AdminUpdateRequest;
import com.dinner_review.dinning.model.entity.Restaurant;
import com.dinner_review.dinning.repository.RestaurantRepository;
import com.dinner_review.dinning.repository.ReviewRepository;

import com.dinner_review.dinning.service.AdminService;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/Admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository){
        this.adminService = new AdminService(reviewRepository, restaurantRepository);
    }

    @GetMapping("")
    public ArrayList<ReviewDTO> getFalseAdminStatusReviews() {
        
        ArrayList<ReviewDTO> falseReviews = this.adminService.getReviewsWithFalseAdminVue();
        return falseReviews;
    }

    @PostMapping("")
    public Iterable<Restaurant> postUpdatingAdminStatusInReviews(@Valid @RequestBody AdminUpdateRequest updates) {
        //TODO: process POST request
        

        return this.adminService.updateRestaurantScores(updates.getReviews());
    }
    
    

}
