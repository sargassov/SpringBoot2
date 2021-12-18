package com.example.springboot1.controllers;

import com.example.springboot1.dto.ProductCartDto;
import com.example.springboot1.dto.ProductDto;
import com.example.springboot1.services.CartService;
import com.example.springboot1.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*Сделал второй контроллер, приблизительно отразил все изменения на фронте,но
бэк в упор отказывается выполнять какие-либо методы нового контроллера.
Пока не могу понять в чем проблема...
*/

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<ProductCartDto> getProductsFromTheCart(){
//        System.out.println("use getAll");
        return cartService.getAll();
    }

    @GetMapping("/{id}")
    public void addById(@PathVariable Long id){
        System.out.println("add");
        cartService.addById(id);
    }

    @DeleteMapping("/{id}")
    public void removeOneWhereIdLike(@PathVariable Long id){
//        System.out.println("remove one");
        cartService.removeOneWhereIdLike(id);
    }

    @DeleteMapping
    public void removeAll(){
//        System.out.println("remove all");
        cartService.removeAll();
    }
}
