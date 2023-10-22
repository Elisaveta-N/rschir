package org.example.controller;

import org.example.model.ProductToBasketDTO;
import org.example.model.Basket;
import org.example.service.BasketService;
import org.example.service.Basket_ProductBaseService;
import org.example.service.ProductBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baskets")
public class BasketController {

    private final BasketService basketService;
    private final ProductBaseService productBaseService;

    private final Basket_ProductBaseService basketProductBaseService;

    public BasketController(BasketService basketService, ProductBaseService productBaseService, Basket_ProductBaseService basketProductBaseService) {
        this.basketService = basketService;
        this.productBaseService = productBaseService;
        this.basketProductBaseService = basketProductBaseService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createBasket() {
        Basket b = basketService.create2(new Basket());

        return b != null
                ? new ResponseEntity<>(b, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("change")
    public ResponseEntity<?> add(@RequestBody ProductToBasketDTO params) {
        try {
            var ret = basketService.addProduct(params);
            return ret.basket.isPresent()
                ? new ResponseEntity<>(ret.basket.get(), ret.status)
                : new ResponseEntity<>(ret.status);
        }
        catch(Exception ex) {
            ;
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("{id}")
    private ResponseEntity<Basket> getOneBasket(@PathVariable Long id) {
        var basket =  basketService.read(id);
        return basket != null && !basket.isEmpty()
                ? new ResponseEntity<>(basket.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{idBasket}/{idProduct}")
    private ResponseEntity<Basket> removeProduct(@PathVariable Long idBasket, @PathVariable Long idProduct) {
        try {
            var ret = basketService.removeProduct(idBasket, idProduct);
            return ret.basket.isPresent()
                    ? new ResponseEntity<>(ret.basket.get(), ret.status)
                    : new ResponseEntity<>(ret.status);
        }
        catch(Exception ex) {
            ;
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("buy/{idBasket}")
    public ResponseEntity<?> buyBasket(@PathVariable Long idBasket) {
        try {
            var ret = basketService.buyBasket(idBasket);
            return ret.basket.isPresent()
                    ? new ResponseEntity<>(ret.basket.get(), ret.status)
                    : new ResponseEntity<>(ret.status);
        }
        catch(Exception ex) {
            ;
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
