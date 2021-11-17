package com.example.springboot1.repositories;

import com.example.springboot1.Product;
import com.example.springboot1.services.ProductService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private ProductService service;
    private List<Product> repository;
    private static long counter = 0;

    public ProductRepository(ProductService service) {
    }

    @PostConstruct
    public void init(ProductService service){
        this.service = service;

        repository = new ArrayList<>(List.of(
                new Product(++counter, "Bread", 39),
                new Product(++counter, "Butter", 139),
                new Product(++counter, "Milk", 79),
                new Product(++counter, "Chocolate", 79),
                new Product(++counter, "Roastbeef", 589),
                new Product(++counter, "Potato", 45),
                new Product(++counter, "Onion", 9),
                new Product(++counter, "Meat", 543),
                new Product(++counter, "Apples", 109)
        ));
    }

    public Product getProductById(long id){
        for(Product p : repository){
            if(p.getId() == id) return p;
        }
        return null;
    }

    public ArrayList<Product> getAllProducts(){
        return (ArrayList<Product>) Collections.unmodifiableList(repository);
    }
}
