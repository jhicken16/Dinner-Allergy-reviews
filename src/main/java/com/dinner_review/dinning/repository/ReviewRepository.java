package com.dinner_review.dinning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.dinner_review.dinning.model.entity.Restaurant;
import com.dinner_review.dinning.model.entity.Review;

import jakarta.transaction.Transactional;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    List<Review> findByRestaurant(Restaurant restaurant);

    List<Review> findByAdminActionTrue();

    List<Review> findByAdminActionFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.adminAction = true WHERE id IN :ids")
    void updateAdminAction(List<Long> ids);
}
