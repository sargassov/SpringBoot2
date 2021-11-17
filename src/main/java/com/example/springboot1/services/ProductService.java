package com.example.springboot1.services;

import com.example.springboot1.Product;
import com.example.springboot1.controllers.TotalController;
import com.example.springboot1.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    private TotalController controller;
    private ProductRepository repository;

    public ProductService(TotalController controller, ProductRepository repository) {
        this.controller = controller;
        this.repository = repository;
    }

    public Product getProductById(Long id){
        return repository.getProductById(id);
    }

    public ArrayList<Product> getAllProducts(){
        return repository.getAllProducts();
    }
}
