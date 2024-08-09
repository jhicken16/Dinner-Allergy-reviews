package com.dinner_review.dinning.model.RequestBodies;

import java.util.ArrayList;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

public class AdminUpdateRequest {

    @NotEmpty
    @Getter @Setter private ArrayList<Long> reviews;

}
