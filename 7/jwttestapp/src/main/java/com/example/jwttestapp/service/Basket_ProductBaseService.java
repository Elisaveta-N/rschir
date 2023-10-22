package com.example.jwttestapp.service;


import com.example.jwttestapp.model.Basket_ProductBase;
import com.example.jwttestapp.repo.Basket_ProductBaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class Basket_ProductBaseService {
    private Basket_ProductBaseRepository basketProductBaseRepository;

    public Basket_ProductBaseService(Basket_ProductBaseRepository basketProductBaseRepository) {
        this.basketProductBaseRepository = basketProductBaseRepository;
    }

    @Transactional
    public boolean create(Basket_ProductBase basket_ProductBase) {
        try {
            basketProductBaseRepository.save(basket_ProductBase);
            try {
                //emailService.sendNotification(basket_ProductBase);
            } catch (Exception e) {
                //log.error("Failed to send email: " + e.getMessage());
            }
            return true;
        }
        catch (Exception e)  {
            //log.error("Failed to save basket_ProductBase: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public Basket_ProductBase create2(Basket_ProductBase basket_ProductBase) {
        try {
            var b = basketProductBaseRepository.save(basket_ProductBase);
            try {
                //emailService.sendNotification(basket_ProductBase);
            } catch (Exception e) {
                //log.error("Failed to send email: " + e.getMessage());
            }
            return b;
        }
        catch (Exception e)  {
            //log.error("Failed to save basket_ProductBase: " + e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Basket_ProductBase> readAll() {
        try {
            //log.info("Read all basket_ProductBases");
            return basketProductBaseRepository.findAll();
        }
        catch (Exception e)  {
            //log.error("Failed to read all basket_ProductBases: " + e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Basket_ProductBase> read(long id) {
        try {
            //log.info("Read basket_ProductBase by id = {}", id);
            return basketProductBaseRepository.findById(id);
        }
        catch (Exception e)  {
            //log.error("Failed to read basket_ProductBase by id: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean update(Basket_ProductBase basket_ProductBase, long id) {
        try {
            //log.info("Update basket_ProductBase by id = {}", id);
            basket_ProductBase.setId(id);
            basketProductBaseRepository.save(basket_ProductBase);
            return true;
        }
        catch (Exception e)  {
            //log.error("Failed to update basket_ProductBase by id: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean delete(long id) {
        //log.info("Delete basket_ProductBase by id = {}", id);
        basketProductBaseRepository.deleteById(id);
        return true;
    }   
}
