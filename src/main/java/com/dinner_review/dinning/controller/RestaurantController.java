package com.dinner_review.dinning.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.dinner_review.dinning.exceptions.RestaurantErrors.RestaurantNotFound;
import com.dinner_review.dinning.model.DTO.RestaurantDTO;
import com.dinner_review.dinning.model.RequestBodies.IdInRequestBody;
import com.dinner_review.dinning.model.RequestBodies.RestaurantCreateRequest;
import com.dinner_review.dinning.model.entity.Restaurant;
import com.dinner_review.dinning.repository.RestaurantRepository;
import com.dinner_review.dinning.service.RestaurantService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/Restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantRepository restaurantRepository){
        this.restaurantService = new RestaurantService(restaurantRepository);
    }

    @GetMapping()                                   //Get all rows from restaurant.
    public ArrayList<RestaurantDTO> getRestaurants(){

        Iterable<Restaurant> restaurantCollection = this.restaurantService.getAllRestaurants();
        
        //Check if data base table is populated
        if(isIterableEmpty(restaurantCollection)){
           throw new RestaurantNotFound("No current available data.");
        }

        ArrayList<RestaurantDTO> rs = new ArrayList<RestaurantDTO>();

        Iterator<Restaurant> i = restaurantCollection.iterator();
        while(i.hasNext()){
            rs.add(new RestaurantDTO(i.next()));
        }
            
        return rs;
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {

        RestaurantDTO r = new RestaurantDTO(this.restaurantService.getRestaurantById(id));
        return r;
    }

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody RestaurantCreateRequest name) {
        
        return new ResponseEntity<Restaurant>(this.restaurantService.createRestaurant(name.getName()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Restaurant putMethodName(@PathVariable Long id, @Valid @RequestBody RestaurantCreateRequest name) {        
        return this.restaurantService.updateRestaurant(id, name.getName());
    }
    
    @GetMapping("/search/{name}")
    public List<Restaurant> getSearchedRestaurants(@PathVariable String name) {
        return this.restaurantService.searchRestaurants(name);
    }

    @DeleteMapping("/Delete")
    public Restaurant deleteRestaurant(@Valid @RequestBody IdInRequestBody restaurantToDelete){
            return this.restaurantService.deleteRestaurant(restaurantToDelete.getId());
    }
    


//                  <-------------- HELPERS ---------------->


    //check if iterable is empty or not.
    private boolean isIterableEmpty(Iterable<Restaurant> i){
        return !i.iterator().hasNext();
    }
    
}
