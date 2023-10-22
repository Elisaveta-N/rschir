package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Telephone;
import org.example.repo.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TelephoneService {
    private final TelephoneRepository telephoneRepository;

    @Autowired
    public TelephoneService(TelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }
    public Optional<Telephone> findById(long id){

        return telephoneRepository.findById(id);
    }

    @Transactional
    public boolean create(Telephone telephone) {
        try {
            log.info("Save telephone");
            telephoneRepository.save(telephone);
            try {
                //emailService.sendNotification(telephone);
            } catch (Exception e) {
                log.error("Failed to send email: " + e.getMessage());
            }
            return true;
        }
        catch (Exception e)  {
            log.error("Failed to save telephone: " + e.getMessage());
            return false;
        }
    }

    @Transactional(readOnly = true)
    public List<Telephone> readAll() {
        try {
            log.info("Read all telephones");
            return telephoneRepository.findAll();
        }
        catch (Exception e)  {
            log.error("Failed to read all telephones: " + e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Telephone> read(long id) {
        try {
            log.info("Read telephone by id = {}", id);
            return telephoneRepository.findById(id);
        }
        catch (Exception e)  {
            log.error("Failed to read telephone by id: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean update(Telephone telephone, long id) {
        try {
            log.info("Update telephone by id = {}", id);
            telephone.setId(id);
            telephoneRepository.save(telephone);
            return true;
        }
        catch (Exception e)  {
            log.error("Failed to update telephone by id: " + e.getMessage());
            return false;
        }
    }
    @Transactional
    public boolean update(Telephone telephone) {
        try {
            log.info("Update telephone");
            telephoneRepository.save(telephone);
            return true;
        }
        catch (Exception e)  {
            log.error("Failed to update telephone" + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean delete(long id) {
        log.info("Delete telephone by id = {}", id);
        telephoneRepository.deleteById(id);
        return true;
    }
}
