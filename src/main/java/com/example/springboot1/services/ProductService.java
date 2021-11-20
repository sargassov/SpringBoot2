package com.example.springboot1.services;

import com.example.springboot1.data.Product;
import com.example.springboot1.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(productRepository.getRepository());
    }

    public void deleteProductById(Long id) {
        List<Product> list = productRepository.getRepository();
        list.removeIf(product -> product.getId() == id);
    }

    public void changeCostById(Long id, Integer delta) {
        Product p = productRepository.getProductById(id);
        int newCost = p.getCost() + delta;
        if(newCost > 0)
            p.setCost(newCost);
    }
}
