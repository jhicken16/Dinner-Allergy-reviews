package com.dinner_review.dinning.model.RequestBodies;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UpdateUserRequest {

    
    @Size(max = 255, min = 0, message = "Username length is to Long")
    @Getter String userName;
    public void setUserName(@Nullable String userName){
        stringNotBlank(userName);
        this.userName = userName;
    }    

    @Size(max = 255, min = 0, message = "Username length is to Long")
    @Getter String city;
    public void setCity(@Nullable String city){
        stringNotBlank(city);
        this.city = city;
    }

    @Size(max = 255, min = 0, message = "Username length is to Long")
    @Getter String county;
    public void setCounty(@Nullable String county){
        stringNotBlank(county);
        this.county = county;
    }

    
    @Size(max = 8, min = 0, message = "Username length is to Long")
    @Pattern(regexp = "^[A-Za-z]{1,2}\\d{1,2}\\s?\\d[A-Za-z]{2}$")
    @Getter String postCode;
    public void setPostCode(@Nullable String postCode){
        stringNotBlank(postCode);
        this.postCode = postCode;
    }

    @Nullable
    @Getter @Setter Boolean peanutAllergy;

    @Nullable
    @Getter @Setter Boolean eggAllergy;

    @Nullable
    @Getter @Setter Boolean dairyAllergy;

    private void stringNotBlank(String s){
        if(s != null && s.trim().isEmpty()){
            throw new IllegalArgumentException("you have only used empty space");
        }
    }

}
