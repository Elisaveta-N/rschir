package com.example.prac7auth.boot;


import com.example.prac7auth.model.Role;
import com.example.prac7auth.model.User;
import com.example.prac7auth.repository.RoleRepository;
import com.example.prac7auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@Slf4j
public class CreateUsers implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

/*    @Autowired
    private BCryptPasswordEncoder passwordEncoder;*/

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // load the roles
            Role admin = roleRepository.findFirstByName("ROLE_ADMIN");
            Role seller = roleRepository.findFirstByName("ROLE_SELLER");
            Role user = roleRepository.findFirstByName("ROLE_USER");

            User adminUser = new User();
            adminUser.setName("admin");
            adminUser.setEmail("admin@example.com");
            //adminUser.setPassword(passwordEncoder.encode("Reindeer Flotilla"));//
            adminUser.setPassword("admin");//
            adminUser.addRole(admin);

            User sellerUser = new User();
            sellerUser.setName("seller");
            sellerUser.setEmail("seller@example.com");
            //sellerUser.setPassword(passwordEncoder.encode("Reindeer Flotilla"));//
            sellerUser.setPassword("seller");//
            sellerUser.addRole(seller);

            User userUser = new User();
            userUser.setName("user");
            userUser.setEmail("user@example.com");
            //userUser.setPassword(passwordEncoder.encode("Reindeer Flotilla"));//
            userUser.setPassword("user");
            userUser.addRole(user);
            //userUser.addRole(seller);

            userRepository.save(adminUser);
            userRepository.save(sellerUser);
            userRepository.save(userUser);

            log.info(">>>> Loaded User Data and Created users...");
        }
    }
}
