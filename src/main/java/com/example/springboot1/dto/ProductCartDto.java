package com.example.springboot1.dto;

import com.example.springboot1.entities.Product;

public class ProductCartDto {
    private Long id;
    private String title;
    private Integer cost;
    private Integer quantity;

    public ProductCartDto() {
    }

    public ProductCartDto(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.quantity = 0;
    }

    public ProductCartDto(ProductDto productDto){
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.cost = productDto.getCost();
        this.quantity = 0;
    }

    public ProductCartDto(Long id, String title, Integer cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.quantity = 0;
    }

    public ProductCartDto(ProductCartDto pco) {
        this.id = pco.id;
        this.title = pco.title;
        this.cost = pco.cost;
        this.quantity = 0;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public int checkQuantity(){
        return quantity * cost;
    }
}
