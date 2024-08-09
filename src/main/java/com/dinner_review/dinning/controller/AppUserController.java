package com.dinner_review.dinning.controller;

import org.springframework.web.bind.annotation.RestController;

import com.dinner_review.dinning.model.RequestBodies.IdInRequestBody;
import com.dinner_review.dinning.model.RequestBodies.UpdateUserRequest;
import com.dinner_review.dinning.model.RequestBodies.UserCreateRequest;
import com.dinner_review.dinning.model.entity.AppUser;
import com.dinner_review.dinning.repository.UserRepository;
import com.dinner_review.dinning.service.AppUserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/user")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(UserRepository userRepository){
        this.appUserService = new AppUserService(userRepository);
    }

    @GetMapping("/{id}")
    public AppUser getUser(@PathVariable Long id) {
        
        //TODO Validation
        return this.appUserService.GetUserById(id);
    }

    @GetMapping("")
    public AppUser getUserByName(@RequestParam String name){
        return this.appUserService.GetUserByName(name);
    }

    @PostMapping("/Create")
    public ResponseEntity<AppUser> createUser(@Valid @RequestBody UserCreateRequest newUser) {
        
        AppUser user = this.appUserService.createNewUser(newUser.getUserName(), newUser.getCity(), newUser.getCounty(), newUser.getPostCode(), newUser.getPeanutAllergy(), newUser.getEggAllergy(), newUser.getDairyAllergy());
        return new ResponseEntity<AppUser>(user, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(
        @PathVariable Long id,
        @Valid @RequestBody UpdateUserRequest updateObj) {
        
        AppUser updatedUser = this.appUserService.updateUser(id, updateObj.getUserName(), updateObj.getCity(), updateObj.getCounty(), updateObj.getPostCode(), updateObj.getPeanutAllergy(), updateObj.getEggAllergy(), updateObj.getDairyAllergy());
        return new ResponseEntity<AppUser>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/Delete")
    public ResponseEntity<AppUser> deleteUser(@Valid @RequestBody IdInRequestBody userId){
        
        // TODO validation

        return new ResponseEntity<AppUser>(this.appUserService.deleteUser(userId.getId()), HttpStatus.OK) ;
    }
}
