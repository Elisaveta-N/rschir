package org.example.repo;

import org.example.model.Basket;
import org.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BasketRepository extends JpaRepository<Basket, Long> {
}
