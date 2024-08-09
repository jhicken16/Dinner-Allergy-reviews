package com.dinner_review.dinning.model.entity;
import java.util.Set;

import java.util.HashSet;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Setter;
import lombok.Getter;

@Entity
@Table
public class Restaurant {

    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    @OneToMany(mappedBy = "restaurant")
    @Getter private Set<Review> reviews = new HashSet<>();

    @Column(nullable = false)
    @Getter @Setter private String name;

    @Column(nullable = false)
    @Getter @Setter private Float peanutScore;

    @Column(nullable = false)
    @Getter @Setter private Float eggScore;

    @Column(nullable = false)
    @Getter @Setter private Float dairyScore;

}
