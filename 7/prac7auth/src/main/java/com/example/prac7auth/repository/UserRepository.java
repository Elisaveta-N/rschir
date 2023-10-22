package com.example.prac7auth.repository;

import com.example.prac7auth.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findFirstByEmail(String email);
    User findFirstByName(String name);
    User findFirstByJwt(String token);
}
