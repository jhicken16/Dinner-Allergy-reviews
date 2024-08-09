package com.dinner_review.dinning.model.RequestBodies;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

public class IdInRequestBody {

    @NotNull
    @Positive
    @Getter @Setter Long id;

}
