package com.example.springboot1.repositories;

import com.example.springboot1.data.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> repository;
    private static long counter;

    public ProductRepository() {
    }

    @PostConstruct
    public void init(){
        repository = new ArrayList<>(List.of(
                new Product(++counter, "Bread", 39),
                new Product(++counter, "Mineral water", 89),
                new Product(++counter, "Butter", 139),
                new Product(++counter, "Potatoes", 69),
                new Product(++counter, "Milk", 59)

        ));
    }

    public List<Product> getRepository(){
        return repository;
    }

    public Product getProductById(Long id){
        for(Product p: repository){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
}
