package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author")
    private String author;
    @Column(name = "seller_num")
    private Short seller_num;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "pr_type")
    private ProductType pr_type;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "name")
    private String name;

}