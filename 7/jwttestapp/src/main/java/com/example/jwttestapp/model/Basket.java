package com.example.jwttestapp.model;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Basket")

public class Basket {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Long id;

    @OneToMany(mappedBy = "basket")
    public Set<Basket_ProductBase> registrations = new HashSet();


    public boolean addToBasket(Basket_ProductBase bpb) {
        boolean bRes = true;
        registrations.add(bpb);
        return bRes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Basket)) return false;

        return id != null && id.equals(((Basket) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }


}
