package com.example.springboot1.controllers;

import com.example.springboot1.data.Product;
import com.example.springboot1.exceptions.ProductNotFoundException;
import com.example.springboot1.services.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/over_min/{value}")
    public List<Product> getAllProductsOverThanMinPrice(@PathVariable Integer value){
        return productService.getAllProductsOverThanMinPrice(value);
    }

    @GetMapping("/products/less_max/{value}")
    public List<Product> getAllProductsLessThanMaxPrice(@PathVariable Integer value){
        return productService.getAllProductsLessThanMaxPrice(value);
    }

    @GetMapping("/products/cost_between")
    public List<Product> getProductsByCostBetween(
            @RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100") Integer max){
        return productService.findByCostBetween(min, max);
    }

    @GetMapping("/products/{id}")
    public Product getProductsById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(()
                -> new ProductNotFoundException("Product #" + id + " not found"));
    }

    @GetMapping("/products/delete/{id}")
    public void getAllProducts(@PathVariable Long id){
        productService.deleteProductById(id);
    }

    @GetMapping("/products/change_cost")
    public void changeCostById(@RequestParam Long id, @RequestParam Integer delta){
        productService.changeCostById(id, delta);
        System.out.println("id = " + id);
        System.out.println("delta = " + delta);
    }
}
