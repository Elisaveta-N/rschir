package com.example.jwttestapp.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;


@Entity
@DiscriminatorValue("PHONE")
@Getter
@Setter
public class Phone extends ProductBase {

    @Column(name = "manufacture")
    private String manufacture;

    @Column(name = "accum_capacity")
    private int accumCapacity;
}
