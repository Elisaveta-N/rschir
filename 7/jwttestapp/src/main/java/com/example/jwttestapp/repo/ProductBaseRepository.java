package com.example.jwttestapp.repo;


import com.example.jwttestapp.model.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBaseRepository extends JpaRepository<ProductBase, Long> {
}
