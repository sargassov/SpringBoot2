package com.example.springboot1.services;

import com.example.springboot1.carts.Cart;
import com.example.springboot1.converters.ProductConverter;
import com.example.springboot1.dto.ProductCartDto;
import com.example.springboot1.dto.ProductDto;
import com.example.springboot1.entities.Product;
import com.example.springboot1.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private Cart cart;
    private ProductService productService;
    private ProductConverter productConverter;

    public CartService(Cart cart, ProductService productService, ProductConverter productConverter) {
        this.cart = cart;
        this.productService = productService;
        this.productConverter = productConverter;
    }

    public List<ProductCartDto> getAll() {
        return cart.getAll();
    }

    public void addById(Long id) {

        ProductCartDto pcd = productConverter
                .productToProductCartDto(productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product #" + id + " not found")));

        cart.add(pcd);

    }

    public void removeOneWhereIdLike(Long id) {
        cart.removeOneWhereIdLike(id);
    }

    public void removeAll() {
        cart.removeAll();
    }
}
