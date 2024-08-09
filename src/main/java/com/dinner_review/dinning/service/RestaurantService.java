package com.dinner_review.dinning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbConflict;
import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbInteractionException;
import com.dinner_review.dinning.exceptions.RestaurantErrors.RestaurantFailedToCreate;
import com.dinner_review.dinning.exceptions.RestaurantErrors.RestaurantNotFound;
import com.dinner_review.dinning.exceptions.ValidationExceptions.NullReferenceException;
import com.dinner_review.dinning.exceptions.userExceptions.UserUnknownError;
import com.dinner_review.dinning.model.entity.Restaurant;
import com.dinner_review.dinning.repository.RestaurantRepository;

public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public Iterable<Restaurant> getAllRestaurants(){
        try{
            Iterable<Restaurant> restaurantCollection = restaurantRepository.findAll();
            return restaurantCollection;
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Get_All_Restaurants - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(Exception e){
            System.out.println("----Exception----Get_All_Restaurants - " + e.getMessage());
            throw new UserUnknownError("Unknown Error");
        }  
    }

    public Restaurant getRestaurantById(Long id){
        try{
            Restaurant restaurant = this.restaurantRepository.findById(id).orElseThrow(() ->
                new RestaurantNotFound("Restaurant was not found.")
            );
            return restaurant;
        }
        catch(RestaurantNotFound e){
            System.out.println("----Exception not found----Get_Restaurant_By_Id - " + e.getMessage());
            throw e;
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Get_Restaurant_By_Id - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(Exception e){
            System.out.println("----Exception----Get_Restaurant_By_Id - " + e.getMessage());
            throw new UserUnknownError("Unknown Error");
        } 

    }

    public Restaurant createRestaurant(String name){

        Restaurant newRestaurant = new Restaurant();
        try{
            //TODO
            //Check if restaurant already exists.
            Optional<Restaurant> restaurantEstists = this.restaurantRepository.findByName(name);
            if(restaurantEstists.isPresent()){
                throw new DbConflict("Restaurant already exists.");
            }

            newRestaurant.setName(name);
            newRestaurant.setPeanutScore((float)0);
            newRestaurant.setEggScore((float)0);
            newRestaurant.setDairyScore((float)0);
            Restaurant restaurantAdded = this.restaurantRepository.save(newRestaurant);
            return restaurantAdded;
        }
        catch(DbConflict e){
            System.out.println("----Exception DbConflict----Create_Restaurant_Error - " + e.getMessage());
            throw new DbConflict(e.getMessage());
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Create_Restaurant_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
            System.out.println("----Exception IllegalArgumentException----Create_Restaurant_Error - " + e.getMessage());
            throw new NullReferenceException(e.getMessage(), "name");
        }
        catch(Exception e){
            System.out.println("----Exception----Create_Restaurant_Error - " + e.getMessage());
            throw new RestaurantFailedToCreate("Unknown Error");
        }
    }

    public Restaurant updateRestaurant(Long id, String name){
        try{
            Optional<Restaurant> r = this.restaurantRepository.findById(id);
            if(r.isEmpty()){
                throw new RestaurantNotFound("That restaurant does not exist,");
            }
            Restaurant restaurant = r.get();
            restaurant.setName(name);

            Restaurant updatedRestaurant = this.restaurantRepository.save(restaurant);
            return updatedRestaurant;
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Update_Restaurant_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
            System.out.println("----Exception IllegalArgumentException----Update_Restaurant_Error - " + e.getMessage());
            throw new NullReferenceException(e.getMessage(), "name");
        }
        catch(Exception e){
            System.out.println("----Exception----Update_Restaurant_Error - " + e.getMessage());
            throw new RestaurantFailedToCreate("Unknown Error did not update");
        }
    }

    public List<Restaurant> searchRestaurants(String searchArg){
        try{
            Optional<List<Restaurant>> searchResults = Optional.ofNullable(this.restaurantRepository.findByNameLike(searchArg));
            if(searchResults.isEmpty()){
                throw new RestaurantNotFound("search was Unsuccessful.");
            }
            return searchResults.get();
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Search_Restaurant_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
            System.out.println("----Exception IllegalArgumentException----Search_Restaurant_Error - " + e.getMessage());
            throw new NullReferenceException(e.getMessage(), "name");
        }
        catch(Exception e){
            System.out.println("----Exception----Search_Restaurant_Error - " + e.getMessage());
           throw new RestaurantNotFound("Restaurant was not found.");
        }

       
    } 
    public Restaurant deleteRestaurant(Long id) {
        try{
            Optional<Restaurant> deletedRestaurant = this.restaurantRepository.findByIdWithReviewEagerly(id);
            if(deletedRestaurant.isEmpty()){
                throw new RestaurantNotFound("Restaurant to delete Not found.");
            }
            
            Restaurant deleted = deletedRestaurant.get();
            this.restaurantRepository.delete(deleted);
            return deleted;
        }
        catch(RestaurantNotFound e){
            System.out.println("----Exception entity to delete----Not_Found_Error - " + e.getMessage());
            throw e;  
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----delete_Restaurant_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
            System.out.println("----Exception IllegalArgumentException----delete_Restaurant_Error - " + e.getMessage());
            throw new NullReferenceException(e.getMessage(), "name");
        }
        catch(Exception e){
            System.out.println("----Exception----delete_Restaurant_Error - " + e.getMessage());
            throw new RestaurantNotFound("Restaurant was not found.");
        }

        
        }

        public Boolean hasUserAlreadyMadeReview(Long restaurant_id, String username){
            try{
                Optional<String> maybeUsername = this.restaurantRepository.findRestaurantByUserName(restaurant_id, username);

                if(maybeUsername.isEmpty()){
                    return false;
                }
                return true;
            }
            catch(DataAccessException e){
                System.out.println("----Exception DataAccessException----Has_User_Made_Review_Error - " + e.getMessage());
                throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
            }
            catch(IllegalArgumentException e){
                System.out.println("----Exception IllegalArgumentException----Has_User_Made_Review_Error - " + e.getMessage());
                throw new NullReferenceException(e.getMessage(), "name");
            }
            catch(Exception e){
                System.out.println("----Exception----Has_User_Made_Review_Error - " + e.getMessage());
                throw new RestaurantNotFound("some think happened.");
            }
    }
}
