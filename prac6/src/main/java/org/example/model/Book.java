package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.controller.BookController;

import java.math.BigDecimal;


@Entity
@DiscriminatorValue("BOOK")
@Getter
@Setter
public class Book extends ProductBase{

    @Column(name = "author")
    private String author;

}
