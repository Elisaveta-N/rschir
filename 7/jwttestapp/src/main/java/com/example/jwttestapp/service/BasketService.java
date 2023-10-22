package com.example.jwttestapp.service;


import com.example.jwttestapp.dto.BasketServiceReturnDTO;
import com.example.jwttestapp.model.*;
import com.example.jwttestapp.repo.BasketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    private BasketRepository basketRepository;
    private final ProductBaseService productBaseService;
    private final Basket_ProductBaseService basketProductBaseService;

    public BasketService(BasketRepository basketRepository, ProductBaseService productBaseService, Basket_ProductBaseService basketProductBaseService) {
        this.basketRepository = basketRepository;
        this.productBaseService = productBaseService;
        this.basketProductBaseService = basketProductBaseService;
    }

    public boolean create(Basket basket) {
        try {
            basketRepository.save(basket);
            try {
                //emailService.sendNotification(book);
            } catch (Exception e) {
                //log.error("Failed to send email: " + e.getMessage());
            }
            return true;
        } catch (Exception e) {
            //log.error("Failed to save book: " + e.getMessage());
            return false;
        }
    }

    public Basket create2(Basket basket) {
        try {
            Basket ret = basketRepository.save(basket);
            return ret;
        } catch (Exception e) {
            System.out.println("Failed to create Basket: " + e.getMessage());
        }
        return null;
    }


    @Transactional(readOnly = true)
    public List<Basket> readAll() {
        try {
            //log.info("Read all books");
            return basketRepository.findAll();
        } catch (Exception e) {
            //log.error("Failed to read all books: " + e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Basket> read(long id) {
        try {
            //log.info("Read book by id = {}", id);
            return basketRepository.findById(id);
        } catch (Exception e) {
            //log.error("Failed to read book by id: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean update(Basket basket) {
        try {
            //log.info("Update user by id = {}", id);
            basketRepository.save(basket);
            return true;
        } catch (Exception e) {
            //log.error("Failed to update user by id: " + e.getMessage());
            return false;
        }
    }

    public BasketServiceReturnDTO addProduct(ProductToBasketDTO params) {
        BasketServiceReturnDTO ret = new BasketServiceReturnDTO();
        ret.status = HttpStatus.BAD_REQUEST;
        ret.basket = Optional.empty();

        var basket_o = basketRepository.findById(params.idBasket);
        if (basket_o.isPresent()) {
            var basket = basket_o.get();
            var product_o = productBaseService.read(params.idProduct);
            if (product_o.isPresent()) {
                ProductBase product = product_o.get();

                var ofind = basket.registrations.stream().filter(b -> (b.productBase.getId()
                        .equals(params.idProduct))).findFirst();


                //такого продукта еще не было в корзине
                if (ofind.isEmpty()) {
                    if (product.storageAmount < params.amount) {
                        ret.status = HttpStatus.BAD_REQUEST;
                        ret.basket = basket_o;
                        return ret; //too many requested
                    }

                    Basket_ProductBase basket_pb = new Basket_ProductBase();
                    basket_pb.setBasket(basket);
                    basket_pb.setProductBase(product);
                    basket_pb.setAmount(params.amount);
                    var bpb = basketProductBaseService.create2(basket_pb);

                    basket.addToBasket(bpb);
                    basketRepository.save(basket);

                    product.storageAmount = product.storageAmount - params.amount;
                    productBaseService.update(product);

                    ret.status = HttpStatus.OK;
                    ret.basket = basketRepository.findById(params.idBasket);
                    return ret; //добавили товар в корзину
                }
                else { //товар уже в корзине - обновляем
                    for (Basket_ProductBase cur : basket.registrations) {
                        if (cur.productBase.getId() == params.idProduct) {
                            if(params.amount > product.storageAmount + cur.amount) {
                                ret.status = HttpStatus.BAD_REQUEST;
                                ret.basket = basket_o;
                                return ret; //too many requested
                            }

                            product.storageAmount = product.storageAmount + cur.amount - params.amount;
                            productBaseService.update(product);

                            cur.amount = params.amount;
                            basketProductBaseService.update(cur, cur.getId());

                            ret.status = HttpStatus.OK;
                            ret.basket = basketRepository.findById(params.idBasket);
                            return ret; //обновили товар в корзине
                        }
                    }
                }




            } else {
                ;//Product not found
            }
        } else {
            //Basket not found;
        }

        return ret;
    }

    public BasketServiceReturnDTO removeProduct(long idBasket, long idProduct) {

        BasketServiceReturnDTO ret = new BasketServiceReturnDTO();
        ret.status = HttpStatus.BAD_REQUEST;
        ret.basket = Optional.empty();

        var basket_o = basketRepository.findById(idBasket);
        if (basket_o.isPresent()) {
            var basket = basket_o.get();
            var product_o = productBaseService.read(idProduct);
            if (product_o.isPresent()) {
                ProductBase product = product_o.get();

                var ofind = basket.registrations.stream().filter(b -> (b.productBase.getId()
                        .equals(idProduct))).findFirst();

                //такого продукта еще не было в корзине
                if (ofind.isEmpty()) {
                        ret.status = HttpStatus.BAD_REQUEST;
                        ret.basket = basket_o;
                        return ret; //
                }

                Iterator<Basket_ProductBase> iter = basket.registrations.iterator();

                while (iter.hasNext()) {
                    var bpb = iter.next();
                    if (bpb.productBase.getId() == idProduct) {
                        product.storageAmount = product.storageAmount + bpb.amount;
                        basketProductBaseService.delete(bpb.getId());

                        iter.remove();
                        basketRepository.save(basket);

                        ret.status = HttpStatus.OK;
                        ret.basket = basketRepository.findById(idBasket);
                        return ret;
                    }
                }
            } else {
                ;//Product not found
            }
        } else {
            //Basket not found;
        }

        return ret;
    }

    public BasketServiceReturnDTO buyBasket(long idBasket) {

        BasketServiceReturnDTO ret = new BasketServiceReturnDTO();
        ret.status = HttpStatus.BAD_REQUEST;
        ret.basket = Optional.empty();

        var basket_o = basketRepository.findById(idBasket);
        if (basket_o.isPresent()) {
            var basket = basket_o.get();

            Iterator<Basket_ProductBase> iter = basket.registrations.iterator();

            while (iter.hasNext()) {
                var bpb = iter.next();
                basketProductBaseService.delete(bpb.getId());
            }
            basket.registrations.clear();
            basketRepository.save(basket);

            ret.status = HttpStatus.OK;
            ret.basket = basketRepository.findById(idBasket);
        }
        return ret;
    }


}

