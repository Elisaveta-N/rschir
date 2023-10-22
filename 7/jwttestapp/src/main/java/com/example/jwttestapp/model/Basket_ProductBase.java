package com.example.jwttestapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Basket_ProductBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productBase_id")
    public ProductBase productBase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    @JsonIgnore
    public Basket basket;

    public int amount;
}
