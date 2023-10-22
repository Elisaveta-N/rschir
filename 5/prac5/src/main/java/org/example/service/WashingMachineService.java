package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.WashingMachine;
import org.example.repo.WashingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WashingMachineService {
    private final WashingMachineRepository washingMachineRepository;

    @Autowired
    public WashingMachineService(WashingMachineRepository washingMachineRepository) {
        this.washingMachineRepository = washingMachineRepository;
    }
    public Optional<WashingMachine> findById(long id){

        return washingMachineRepository.findById(id);
    }

    @Transactional
    public boolean create(WashingMachine washingMachine) {
        try {
            log.info("Save washingMachine");
            washingMachineRepository.save(washingMachine);
            try {
                //emailService.sendNotification(washingMachine);
            } catch (Exception e) {
                log.error("Failed to send email: " + e.getMessage());
            }
            return true;
        }
        catch (Exception e)  {
            log.error("Failed to save washingMachine: " + e.getMessage());
            return false;
        }
    }

    @Transactional(readOnly = true)
    public List<WashingMachine> readAll() {
        try {
            log.info("Read all washingMachines");
            return washingMachineRepository.findAll();
        }
        catch (Exception e)  {
            log.error("Failed to read all washingMachines: " + e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Optional<WashingMachine> read(long id) {
        try {
            log.info("Read washingMachine by id = {}", id);
            return washingMachineRepository.findById(id);
        }
        catch (Exception e)  {
            log.error("Failed to read washingMachine by id: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean update(WashingMachine washingMachine, long id) {
        try {
            log.info("Update washingMachine by id = {}", id);
            washingMachine.setId(id);
            washingMachineRepository.save(washingMachine);
            return true;
        }
        catch (Exception e)  {
            log.error("Failed to update washingMachine by id: " + e.getMessage());
            return false;
        }
    }
    @Transactional
    public boolean update(WashingMachine washingMachine) {
        try {
            log.info("Update washingMachine");
            washingMachineRepository.save(washingMachine);
            return true;
        }
        catch (Exception e)  {
            log.error("Failed to update washingMachine" + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean delete(long id) {
        log.info("Delete washingMachine by id = {}", id);
        washingMachineRepository.deleteById(id);
        return true;
    }
}
