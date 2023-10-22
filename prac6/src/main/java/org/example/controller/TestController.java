package org.example.controller;


import org.example.model.*;
import org.example.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {



    private final BasketService basketService;

    private final Basket_ProductBaseService basketProductBaseService;

    private final BookService bookService;

    public TestController(BasketService basketService, Basket_ProductBaseService basketProductBaseService, BookService bookService) {

        this.basketService = basketService;
        this.basketProductBaseService = basketProductBaseService;

        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Basket>> test()
    {
        Book book1 = new Book();
        book1.setAuthor("Lev Tolstoy");
        book1.setSellerNumber("IBS304851");
        book1.setPrice(BigDecimal.valueOf(99));
        book1.setName("Sevastopol Sketches");
        book1.setStorageAmount(5);

        var book = bookService.create2(book1);

        var basket = basketService.create2(new Basket());


        Basket_ProductBase basket_pb = new Basket_ProductBase();
        basket_pb.setProductBase(book1);
        basket_pb.setAmount(10);
        basket_pb.basket = basket;
        var bpb = basketProductBaseService.create2(basket_pb);

        basket.addToBasket(bpb);


        var ret = basketService.readAll();

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping("basket/create")
    public ResponseEntity<Basket> createBasket() {
        Basket b = basketService.create2(new Basket());

        return b != null
                ? new ResponseEntity<>(b, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
