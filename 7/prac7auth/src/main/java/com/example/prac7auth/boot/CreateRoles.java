package com.example.prac7auth.boot;

import com.example.prac7auth.model.Role;
import com.example.prac7auth.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class CreateRoles implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public CreateRoles(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role adminRole = Role.builder().name("ROLE_ADMIN").build();
            Role sellerRole = Role.builder().name("ROLE_SELLER").build();
            Role userRole = Role.builder().name("ROLE_USER").build();
            roleRepository.save(adminRole);
            roleRepository.save(sellerRole);
            roleRepository.save(userRole);
            log.info(">>>> Created admin, seller, user roles...");
        }
    }
}
