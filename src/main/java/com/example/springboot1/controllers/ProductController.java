package com.example.springboot1.controllers;

import com.example.springboot1.data.Product;
import com.example.springboot1.exceptions.ProductNotFoundException;
import com.example.springboot1.services.ProductService;
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

    @GetMapping("/products/set_param")
    public Integer setParamRequest(@RequestParam Integer delta){
        System.out.println(" delta = " + delta);
        return productService.setCountParam(delta);
    }

    @GetMapping("/products/param_request")
    public Integer paramRequest(){
        return productService.getCountParam();
    }

    @GetMapping("/products/new")
    public Product addNewProduct(@RequestParam String title, @RequestParam Integer cost){
        return productService.addNewProduct(title, cost);
    }

    @GetMapping("products/sort")
    public List<Product> getAllProductsWithSort(@RequestParam(defaultValue = "0") Integer minCost, @RequestParam(defaultValue = "1000") Integer maxCost){
        List<Product> products = productService.findByCostBetween(minCost, maxCost);
        System.out.println("products = " + products.size());
        return products;
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

    @GetMapping("/products/delete")
    public void deleteProductById(@RequestParam Long id){
        productService.deleteProductById(id);
    }

    @GetMapping("/products/change_cost")
    public void changeCostById(@RequestParam Long id, @RequestParam Integer delta){
        productService.changeCostById(id, delta);
        System.out.println("id = " + id);
        System.out.println("delta = " + delta);
    }
}
