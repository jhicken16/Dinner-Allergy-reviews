package com.dinner_review.dinning.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
public abstract class AdminReview {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @Getter @Setter private Boolean adminAction;
}
