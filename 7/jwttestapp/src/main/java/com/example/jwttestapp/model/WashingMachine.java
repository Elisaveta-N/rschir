package com.example.jwttestapp.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("WMACHINE")
@Getter
@Setter
public class WashingMachine extends ProductBase{

    @Column(name = "manufacture")
    private String manufacture;

    @Column(name = "tank_capacity")
    private int tankCapacity;
}
