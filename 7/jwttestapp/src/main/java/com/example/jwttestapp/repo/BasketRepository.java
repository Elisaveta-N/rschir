package com.example.jwttestapp.repo;


import com.example.jwttestapp.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  BasketRepository extends JpaRepository<Basket, Long> {
}
