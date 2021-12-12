package com.example.springboot1.entities;

import com.example.springboot1.dto.ProductDto;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "secret_info")
    private String secretInfo;

    public Product() {
    }

    public Product(String title, Integer cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(ProductDto productDto) {
        this.title = productDto.getTitle();
        this.cost = productDto.getCost();
        this.secretInfo = "some info";
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

    public String getSecretInfo() {
        return secretInfo;
    }

    public void setSecretInfo(String secretInfo) {
        this.secretInfo = secretInfo;
    }
}
