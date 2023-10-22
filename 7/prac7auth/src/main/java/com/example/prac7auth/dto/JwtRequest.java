package com.example.prac7auth.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}