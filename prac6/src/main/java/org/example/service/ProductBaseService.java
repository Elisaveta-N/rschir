package org.example.service;

import org.example.model.Book;
import org.example.model.ProductBase;
import org.example.repo.ProductBaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductBaseService {
    private final ProductBaseRepository productBaseRepository;

    public ProductBaseService(ProductBaseRepository productBaseRepository) {
        this.productBaseRepository = productBaseRepository;
    }

    @Transactional(readOnly = true)
    public Optional<ProductBase> read(long id) {
        try {
            //log.info("Read book by id = {}", id);
            return productBaseRepository.findById(id);
        }
        catch (jakarta.persistence.EntityNotFoundException e)  {
            //log.error("Failed to read book by id: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean update(ProductBase product) {
        try {
            //log.info("Update book by id = {}", id);
            productBaseRepository.save(product);
            return true;
        }
        catch (Exception e)  {
            //log.error("Failed to update book by id: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean delete(long id) {
        //log.info("Delete scheduler by id = {}", id);
        productBaseRepository.deleteById(id);
        return true;
    }
}
