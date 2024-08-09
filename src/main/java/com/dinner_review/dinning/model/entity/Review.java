package com.dinner_review.dinning.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.dinner_review.dinning.model.AdminReview;
import com.fasterxml.jackson.annotation.JsonBackReference;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import lombok.Setter;
import lombok.Getter;

@Entity
@Table
public class Review extends AdminReview{
    
    @Column
    @Id
    @GeneratedValue
    @Getter @Setter private Long id;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @JsonBackReference
    @Getter @Setter private Restaurant restaurant;

    @Column
    @Getter @Setter private String userName;

    @Column
    @Getter @Setter private int peanutScore;
    
    @Column
    @Getter @Setter private int eggScore;
    
    @Column
    @Getter @Setter private int dairyScore;
    
    @Column(nullable = true)
    @Getter @Setter private String comments;

}
