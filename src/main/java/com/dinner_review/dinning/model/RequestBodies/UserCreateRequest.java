package com.dinner_review.dinning.model.RequestBodies;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UserCreateRequest {

    @NotBlank(message = "User name is mandatory.")
    @Size(max = 255, min = 0, message = "Username length is to Long")
    @Getter @Setter String userName;

    @NotBlank
    @Size(max = 255, min = 0, message = "City length is to Long")
    @Getter @Setter String city;

    @NotBlank
    @Size(max = 255, min = 0, message = "County length is to Long")
    @Getter @Setter String county;

    @NotBlank
    @Size(max = 8, min = 0, message = "Post Code length is to Long")
    @Pattern(regexp = "^[A-Za-z]{1,2}\\d{1,2}\\s?\\d[A-Za-z]{2}$")
    @Getter @Setter String postCode;

    @NotNull
    @Getter @Setter Boolean peanutAllergy;

    @NotNull
    @Getter @Setter Boolean eggAllergy;

    @NotNull
    @Getter @Setter Boolean dairyAllergy;
}
