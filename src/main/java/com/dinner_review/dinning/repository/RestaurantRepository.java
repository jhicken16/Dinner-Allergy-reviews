package com.dinner_review.dinning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dinner_review.dinning.model.entity.Restaurant;


public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    List<Restaurant> findByNameLike(String name);
    Optional<Restaurant> findByName(String name);

    @Query("Select r From Restaurant r LEFT JOIN Fetch r.reviews where r.id = :id")
    Optional<Restaurant> findByIdWithReviewEagerly(@Param("id") Long id);

    @Query("SELECT rev.userName FROM Restaurant r JOIN Review rev ON r.id = rev.restaurant.id WHERE r.id = :id AND rev.userName = :userName")
    Optional<String> findRestaurantByUserName(@Param("id") Long id, @Param("userName") String userName);

    //SELECT DISTINCT RESTAURANT.DAIRY_SCORE, RESTAURANT.EGG_SCORE, RESTAURANT.PEANUT_SCORE, RESTAURANT.ID, RESTAURANT.NAME FROM RESTAURANT JOIN REVIEW ON RESTAURANT.ID = REVIEW.RESTAURANT_ID WHERE REVIEW.ID IN (1, 2, 3, 4) ;
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN Review rev On r.id = rev.restaurant.id WHERE rev.id IN :review_ids")
    Optional<List<Restaurant>> findRestaurantByReviewId(List<Long> review_ids);
}
