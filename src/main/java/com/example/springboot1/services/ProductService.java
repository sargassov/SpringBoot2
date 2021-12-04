package com.example.springboot1.services;

import com.example.springboot1.data.Product;
import com.example.springboot1.exceptions.ProductNotFoundException;
import com.example.springboot1.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id) {
//        List<Product> list = productRepository.getRepository();
//        list.removeIf(product -> product.getId() == id);
    }

    @Transactional
    public void changeCostById(Long id, Integer delta) {
        Product p = productRepository.findById(id).orElseThrow(()
                -> new ProductNotFoundException("Impossible to change cost of product #" + id + ". It not found"));
        p.setCost(p.getCost() + delta);
        //productRepository.save(p);
    }

    public List<Product> getAllProductsOverThanMinPrice(Integer min) {
        List<Product> pp = productRepository.findAll().stream().filter(product -> product.getCost() > min).collect(Collectors.toList());
        System.out.println("pp size = " + pp.size());
        return productRepository.findAll().stream().filter(product -> product.getCost() > min).collect(Collectors.toList());
    }

    public List<Product> getAllProductsLessThanMaxPrice(Integer max) {
        return productRepository.findAll().stream().filter(product -> product.getCost() < max).collect(Collectors.toList());
    }

    public List<Product> findByCostBetween(Integer min, Integer max) {
        return productRepository.findAllByCostBetween(min, max);
    }
}
