package com.example.jwttestapp.model;



import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("BOOK")
@Getter
@Setter
public class Book extends ProductBase{

    @Column(name = "author")
    private String author;

}
