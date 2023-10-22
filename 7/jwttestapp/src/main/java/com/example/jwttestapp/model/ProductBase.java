package com.example.jwttestapp.model;


import lombok.Data;


import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity(name="products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type",
        discriminatorType = DiscriminatorType.STRING)
public class ProductBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sellerNumber")
    private String sellerNumber;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "name")
    private String name;

    @Column(name = "storage_amount")
    public int storageAmount;
}
