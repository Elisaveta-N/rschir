package org.example.repo;

import org.example.model.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBaseRepository extends JpaRepository<ProductBase, Long> {
}
