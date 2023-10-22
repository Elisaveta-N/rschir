package com.example.prac7auth.dto;

import com.example.prac7auth.model.Role;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDto {
    private String name;
    private List<String> roles = new ArrayList<>();

    public void addRole(String role) {
        roles.add(role);
    }
}
