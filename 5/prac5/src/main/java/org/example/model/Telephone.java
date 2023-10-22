package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "telephones")
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "producer")
    private String producer;
    @Column(name = "battery_capacity")
    private Short battery_capacity;
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
