package com.example.springboot1.controllers;

import com.example.springboot1.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TotalController {
    private ProductService service;

    public TotalController(ProductService productService){
        this.service = productService;
    }

    @GetMapping("/products/{id}/info")
    public String viewCurrentProduct(@PathVariable Long id){

        return "viewCurrentProduct";
    }

    @GetMapping("/products")
    public String viewAllProducts(){

        return "viewAllProduct";
    }



}
