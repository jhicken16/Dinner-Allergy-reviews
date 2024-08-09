package com.dinner_review.dinning.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
public class AppUser {
    
    @Id
    @GeneratedValue
    @Getter private Long id;

    @Column(nullable = false)
    @Getter @Setter private String userName;

    @Column(nullable = false)
    @Getter @Setter private String city;

    @Column(nullable = false)
    @Getter @Setter private String county;

    @Column(nullable = false)
    @Getter @Setter private String postCode;

    @Column(nullable = false)
    @Getter @Setter private Boolean peanutAllergy;

    @Column(nullable = false)
    @Getter @Setter private Boolean eggAllergy;

    @Column(nullable = false)
    @Getter @Setter private Boolean dairyAllergy;

}
