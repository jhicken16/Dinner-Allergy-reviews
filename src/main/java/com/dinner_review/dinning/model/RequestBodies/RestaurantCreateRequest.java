package com.dinner_review.dinning.model.RequestBodies;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public class RestaurantCreateRequest {

    
    @NotNull(message = "Must provide Restaurant name.")
    @NotBlank(message = "Restaurant must not be blank.")
    @Size(min = 0, max = 255, message = "Your Restaurant name is to long.")
    @Getter @Setter private String name;
}
