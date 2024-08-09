package com.dinner_review.dinning.repository;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dinner_review.dinning.model.entity.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
    
    Optional<AppUser> findByUserName(String name);
}
