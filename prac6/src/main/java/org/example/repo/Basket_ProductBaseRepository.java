package org.example.repo;

import org.example.model.Basket_ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Basket_ProductBaseRepository extends JpaRepository<Basket_ProductBase, Long>    {
    }

