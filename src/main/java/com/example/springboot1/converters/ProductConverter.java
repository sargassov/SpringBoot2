package com.example.springboot1.converters;

import com.example.springboot1.dto.ProductCartDto;
import com.example.springboot1.dto.ProductDto;
import com.example.springboot1.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product productDtoToProduct(ProductDto productDto){
        return new Product(productDto);
    }

    public ProductDto productToProductDto (Product product){
        return new ProductDto(product);
    }

    public ProductCartDto productToProductCartDto (Product product){
        return new ProductCartDto(product);
    }
}
