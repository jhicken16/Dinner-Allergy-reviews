package com.dinner_review.dinning.model.RequestBodies;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class CreateReviewRequestBody {

    @NotNull(message = "Is Required.")
    @Positive(message = "Not a valid Id")
    @Getter @Setter Long restaurantId;

    @NotNull(message = "Is Required.")
    @Max(5)
    @PositiveOrZero
    @Getter @Setter int peanutScore;

    @NotNull(message = "Is Required.")
    @Max(5)
    @PositiveOrZero
    @Getter @Setter int eggScore;

    @NotNull(message = "Is Required.")
    @Max(5)
    @PositiveOrZero
    @Getter @Setter int dairyScore;

    @Nullable
    @Size(max = 255, message = "comment is to long")
    @Getter @Setter String comments;

}
