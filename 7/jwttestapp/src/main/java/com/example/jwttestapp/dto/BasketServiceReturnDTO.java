package com.example.jwttestapp.dto;

import com.example.jwttestapp.model.Basket;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class BasketServiceReturnDTO {
    public HttpStatus status;
    public Optional<Basket> basket;
}
