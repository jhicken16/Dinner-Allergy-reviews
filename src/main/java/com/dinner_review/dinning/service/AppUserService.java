package com.dinner_review.dinning.service;

import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbConflict;
import com.dinner_review.dinning.exceptions.DatabaseExceptions.DbInteractionException;
import com.dinner_review.dinning.exceptions.ValidationExceptions.NullReferenceException;
import com.dinner_review.dinning.exceptions.userExceptions.UserNotFound;
import com.dinner_review.dinning.exceptions.userExceptions.UserUnknownError;
import com.dinner_review.dinning.model.entity.AppUser;
import com.dinner_review.dinning.repository.UserRepository;

import io.micrometer.common.lang.Nullable;

public class AppUserService {
    private final UserRepository userRepository;

    public AppUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Create 
    public AppUser createNewUser(
        String userName, 
        String City,
        String county,
        String postCode,
        Boolean peanutAllergy,
        Boolean eggAllergy,
        Boolean dairyAllergy){
        
        if(checkUserExists(userName)){
            throw new DbConflict("User Already Exists");
        }

        AppUser newUser = new AppUser();
        newUser.setUserName(userName);
        newUser.setCity(City);
        newUser.setCounty(county);
        newUser.setPostCode(postCode);
        newUser.setPeanutAllergy(peanutAllergy);
        newUser.setEggAllergy(eggAllergy);
        newUser.setDairyAllergy(dairyAllergy);

        try{
            AppUser settingUser = this.userRepository.save(newUser);
            return settingUser;
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Check_User_Exists_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
            System.out.println("----Exception IllegalArgumentException----Check_User_Exists_Error - " + e.getMessage());
            throw new NullReferenceException(e.getMessage(), "name");
        }
        catch(Exception e){
            System.out.println("----Exception----Check_User_Exists_Error - " + e.getMessage());
            throw new UserUnknownError("Unknown Error did not update");
        }
    }



    /*  -------------   Read   ---------------- */ 
    //Read By Id
    public AppUser GetUserById(Long id){
        try{
            Optional<AppUser> mightBeUser = this.userRepository.findById(id);
            if(mightBeUser.isEmpty()){
                throw new UserNotFound("User has not be found.");
            }
            AppUser user = mightBeUser.get();
            return user;
        }
        catch(UserNotFound ex){
            System.out.println("----Exception user not found----Get_user_By_ID_Error - " + ex.getMessage());
            throw ex;
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Get_user_By_ID_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
            System.out.println("----Exception IllegalArgumentException----Get_user_By_ID_Error - " + e.getMessage());
            throw new NullReferenceException(e.getMessage(), "name");
        }
        catch(Exception e){
            System.out.println("----Exception----Get_user_By_ID_Error - " + e.getMessage());
            throw new UserUnknownError("Unknown Error did not update");
        }
    }

    public AppUser GetUserByName(String userName){
        try{

            Optional<AppUser> mightBeUser = this.userRepository.findByUserName(userName);
            if(mightBeUser.isEmpty()){
                throw new UserNotFound("User has not be found.");
            }
            AppUser user = mightBeUser.get();
            return user;
        }
        catch(UserNotFound ex){
            System.out.println("----Exception user not found----Get_user_UserName_Error - " + ex.getMessage());
            throw ex;
        }
        catch(DataAccessException e){
            System.out.println("----Exception DataAccessException----Get_user_By_UserName_Error - " + e.getMessage());
            throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
                System.out.println("----Exception IllegalArgumentException----Get_user_By_UserName_Error - " + e.getMessage());
                throw new NullReferenceException(e.getMessage(), "name");
        }
        catch(Exception e){
                System.out.println("----Exception----Get_user_By_UserName_Error - " + e.getMessage());
                throw new UserUnknownError("Unknown Error.");
        }
    }
        
    //Read function to check if user exists.
    private Boolean checkUserExists(String name){
        try{
            Optional<AppUser> user = this.userRepository.findByUserName(name);
            if(user.isEmpty()){
                return false;
            }
            return true;
        }
        catch(DataAccessException e){
        System.out.println("----Exception DataAccessException----Check_User_Exists_Error - " + e.getMessage());
        throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
        }
        catch(IllegalArgumentException e){
            System.out.println("----Exception IllegalArgumentException----Check_User_Exists_Error - " + e.getMessage());
            throw new NullReferenceException(e.getMessage(), "name");
        }
        catch(Exception e){
            System.out.println("----Exception----Check_User_Exists_Error - " + e.getMessage());
            throw new UserUnknownError("Unknown Error did not update");
        }
    }
    //Update    TODO
    public AppUser updateUser(
        Long id,
        @Nullable String userName,
        @Nullable String city,
        @Nullable String county,
        @Nullable String postCode,
        @Nullable Boolean peanutAllergy,
        @Nullable Boolean eggAllergy,
        @Nullable Boolean dairyAllergy
        ){
            AppUser userToUpdate;
            try{
                Optional<AppUser> mightBeUser = this.userRepository.findById(id);
                if(mightBeUser.isEmpty()){
                    throw new UserNotFound("User does not exist To Update");
                }
                userToUpdate = mightBeUser.get();
            }
            catch(DataAccessException e){
                System.out.println("----Exception DataAccessException----Update_User_Check_User_Exists_Error - " + e.getMessage());
                throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
            }

            //If's to determine what needs updating.
            if(userName != null){
                userToUpdate.setUserName(userName);
            }
            if(city != null){
                userToUpdate.setCity(city);
            }
            if(county != null){
                userToUpdate.setCounty(county);
            }
            if(postCode != null){
                userToUpdate.setPostCode(postCode);
            }
            if(peanutAllergy != null){
                userToUpdate.setPeanutAllergy(peanutAllergy);
            }
            if(eggAllergy != null){
                userToUpdate.setEggAllergy(eggAllergy);
            }
            if(dairyAllergy != null){
                userToUpdate.setDairyAllergy(dairyAllergy);
            }

            try{
                AppUser updatedUser = this.userRepository.save(userToUpdate);
                return updatedUser;
            }
            catch(DataAccessException e){
                System.out.println("----Exception DataAccessException----Save_Updated_User_Error - " + e.getMessage());
                throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
            }
            catch(IllegalArgumentException e){
                System.out.println("----Exception IllegalArgumentException----Updating_User_Error - " + e.getMessage());
                throw new NullReferenceException(e.getMessage(), "To many potentials.");
            }
            catch(Exception e){
                System.out.println("----Exception----Updating_User_Error - " + e.getMessage());
                throw new UserUnknownError("Unknown Error did not update");
            }
        }
    
        //Delete    TODO
        public AppUser deleteUser(Long id){
            
            AppUser userToDelete;
            try{
                Optional<AppUser> mightBeUser = this.userRepository.findById(id);
                if(mightBeUser.isEmpty()){
                    throw new UserNotFound("User does not exist To Delete");
                }
                userToDelete = mightBeUser.get();
            }
            catch(UserNotFound ex){
                System.out.println("----Exception user not found----Delete_user_By_ID_Error - " + ex.getMessage());
                throw ex;
            }
            catch(IllegalArgumentException e){
                System.out.println("----Exception IllegalArgumentException----Delete_User_Error - " + e.getMessage());
                throw new NullReferenceException(e.getMessage(), "id");
            }
            catch(DataAccessException e){
                System.out.println("----Exception DataAccessException----Delete_User_Check_User_Exists_Error - " + e.getMessage());
                throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
            }
            catch(Exception e){
                System.out.println("----Exception----Deleting_Checking_User_Error - " + e.getMessage());
                throw new UserUnknownError("Unknown Error");
            }

            try{
                this.userRepository.delete(userToDelete);
                return userToDelete;
            }            
            catch(DataAccessException e){
                System.out.println("----Exception DataAccessException----Delete_User_Error - " + e.getMessage());
                throw new DbInteractionException(e.getMessage() + "It's not you it's me.");
            }

            catch(Exception e){
                System.out.println("----Exception----Delete_User_Error - " + e.getMessage());
                throw new UserUnknownError("Unknown Error did not Delete");
            }

        }
}
