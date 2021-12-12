package com.example.springboot1.dto;

import com.example.springboot1.entities.Product;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductDto {

    private Long id;
    private String title;
    private Integer cost;

    public ProductDto() {
    }

    public ProductDto(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
