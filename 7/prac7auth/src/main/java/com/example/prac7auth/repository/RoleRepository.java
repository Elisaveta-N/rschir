package com.example.prac7auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.prac7auth.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    Role findFirstByName(String roleAdmin);
}
