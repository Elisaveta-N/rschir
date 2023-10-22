package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
