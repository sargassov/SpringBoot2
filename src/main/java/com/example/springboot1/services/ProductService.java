package com.example.springboot1.services;

import com.example.springboot1.data.Product;
import com.example.springboot1.exceptions.ProductNotFoundException;
import com.example.springboot1.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private Integer countParam;

    public Integer setCountParam(Integer delta) {
        int productsValue = productRepository.findAll().size();
        countParam += delta;

        if(countParam < 0){
            if(productsValue % 10 == 0) countParam = productsValue / 10 - 1;
            else countParam = productsValue / 10;
        }
        else if((countParam == productsValue / 10 && productsValue % 10 == 0)
                || countParam == productsValue / 10 + 1 && productsValue % 10 != 0){
            countParam = 0;
        }
        System.out.println("итоговый = " + countParam);
        return countParam;
    }

    public Integer getCountParam() {
        return countParam;
    }

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        countParam = 0;
    }

    @Transactional
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return selectionForTen(allProducts);
    }

    private List<Product> selectionForTen(List<Product> allProducts) {
        List<Product> answer = new ArrayList<>();
        for(int x = countParam * 10; x < countParam * 10 + 10; x++){
            if(x == allProducts.size()) break;
            answer.add(allProducts.get(x));
        }
        return answer;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void changeCostById(Long id, Integer delta) {
        Product p = productRepository.findById(id).orElseThrow(()
                -> new ProductNotFoundException("Impossible to change cost of product #" + id + ". It not found"));
        p.setCost(p.getCost() + delta);
    }

    public List<Product> findByCostBetween(Integer min, Integer max) {
        List<Product> sortedProducts = productRepository.findAllByCostBetween(min, max);
        return selectionForTen(sortedProducts);
    }

    public Product addNewProduct(String title, Integer cost) {
        return productRepository.save(new Product(title, cost));
    }
}
