package org.example.model;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class BasketServiceReturnDTO {
    public HttpStatus status;
    public Optional<Basket> basket;
}
