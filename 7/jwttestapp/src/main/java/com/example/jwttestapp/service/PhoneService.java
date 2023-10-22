package com.example.jwttestapp.service;


import com.example.jwttestapp.model.Phone;
import com.example.jwttestapp.repo.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class PhoneService {

    private PhoneRepository phoneRepository;


    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public boolean create(Phone phone) {
        try {
            phoneRepository.save(phone);
            try {
                //emailService.sendNotification(phone);
            } catch (Exception e) {
                //log.error("Failed to send email: " + e.getMessage());
            }
            return true;
        }
        catch (Exception e)  {
            //log.error("Failed to save phone: " + e.getMessage());
            return false;
        }
    }

    @Transactional(readOnly = true)
    public List<Phone> readAll() {
        try {
            //log.info("Read all phones");
            return phoneRepository.findAll();
        }
        catch (Exception e)  {
            //log.error("Failed to read all phones: " + e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Phone> read(long id) {
        try {
            //log.info("Read phone by id = {}", id);
            return phoneRepository.findById(id);
        }
        catch (Exception e)  {
            //log.error("Failed to read phone by id: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean update(Phone phone, long id) {
        try {
            //log.info("Update phone by id = {}", id);
            phone.setId(id);
            phoneRepository.save(phone);
            return true;
        }
        catch (Exception e)  {
            //log.error("Failed to update phone by id: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean delete(long id) {
        //log.info("Delete phone by id = {}", id);
        phoneRepository.deleteById(id);
        return true;
    }    
}
