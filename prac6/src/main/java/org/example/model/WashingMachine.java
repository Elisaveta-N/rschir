package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
