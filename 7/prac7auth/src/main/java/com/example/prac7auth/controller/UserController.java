package com.example.prac7auth.controller;

import com.example.prac7auth.dto.UserDto;
import com.example.prac7auth.model.Role;
import com.example.prac7auth.model.User;
import com.example.prac7auth.repository.RoleRepository;
import com.example.prac7auth.repository.UserRepository;
import com.example.prac7auth.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.prac7auth.dto.JwtRequest;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @GetMapping
    public Iterable<User> all() {
        return userRepository.findAll();
    }

    @PutMapping("{user_name}")
    public ResponseEntity<?> update(@PathVariable String user_name, @RequestBody String role_name) {

        Role role = roleRepository.findFirstByName(role_name);
        if(role == null)
            return new ResponseEntity<>("Bad role name", HttpStatus.BAD_REQUEST);

        User user = userRepository.findFirstByName(user_name);
        if(user == null)
            return new ResponseEntity<>("Bad user name", HttpStatus.BAD_REQUEST);

        try {
            User ret = user.addRole(role);
            userRepository.save(ret);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        User user = userRepository.findFirstByName(authRequest.getUsername());
        if(user == null) {
            return new ResponseEntity<>("Bad user name", HttpStatus.FORBIDDEN);
        }
        if(!user.getPassword().equals(authRequest.getPassword())) {
            return new ResponseEntity<>("Bad password", HttpStatus.FORBIDDEN);
        }

        String token = jwtTokenUtils.generateToken(user);
        user.setJwt(token);
        userRepository.save(user);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/validate")
    ResponseEntity<?> validate(@RequestBody String token) {

        User user = userRepository.findFirstByJwt(token);
        if(user== null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setRoles(user.getRoles().stream().map(role -> {return role.getName();})
                .collect(Collectors.toList()));

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}