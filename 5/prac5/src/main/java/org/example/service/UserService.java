package org.example.service;


import lombok.extern.slf4j.Slf4j;

import org.example.model.User;
import org.example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> findByLogin(String currentUserName) {

        return userRepository.findByLogin(currentUserName);
    }

    public Optional<User> findById(long id) {

        return userRepository.findById(id);
    }

    @Transactional
    public boolean create(User user) {
        try {
            log.info("Save user");
            userRepository.save(user);
            try {
                //emailService.sendNotification(user);
            } catch (Exception e) {
                log.error("Failed to send email: " + e.getMessage());
            }
            return true;
        }
        catch (Exception e)  {
            log.error("Failed to save user: " + e.getMessage());
            return false;
        }
    }

    @Transactional(readOnly = true)
    public List<User> readAll() {
        try {
            log.info("Read all users");
            return userRepository.findAll();
        }
        catch (Exception e)  {
            log.error("Failed to read all users: " + e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Optional<User> read(long id) {
        try {
            log.info("Read user by id = {}", id);
            return userRepository.findById(id);
        }
        catch (Exception e)  {
            log.error("Failed to read user by id: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean update(User user, long id) {
        try {
            log.info("Update user by id = {}", id);
            user.setId(id);
            userRepository.save(user);
            return true;
        }
        catch (Exception e)  {
            log.error("Failed to update user by id: " + e.getMessage());
            return false;
        }
    }
    @Transactional
    public boolean update(User user) {
        try {
            log.info("Update user");
            userRepository.save(user);
            return true;
        }
        catch (Exception e)  {
            log.error("Failed to update user" + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean delete(long id) {
        log.info("Delete user by id = {}", id);
        userRepository.deleteById(id);
        return true;
    }

}
