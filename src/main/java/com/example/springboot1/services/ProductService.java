package com.example.springboot1.services;

import com.example.springboot1.converters.ProductConverter;
import com.example.springboot1.dto.ProductDto;
import com.example.springboot1.entities.Product;
import com.example.springboot1.exceptions.ProductNotFoundException;
import com.example.springboot1.repositories.ProductRepository;
import com.example.springboot1.repositories.specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductConverter productConverter;

    public ProductService(ProductRepository productR, ProductConverter productC){
        this.productRepository = productR;
        this.productConverter = productC;
    }

    public Page<Product> getAllProducts(Integer page, Integer minPrice, Integer maxPrice, String titlePart){
        Specification<Product> specification = Specification.where(null);

        if(minPrice != null)
            specification = specification.and(ProductSpecifications.priceGreatedOrEqualThan(minPrice));

        if(maxPrice != null)
            specification = specification.and(ProductSpecifications.priceLessOrEqualThan(maxPrice));

        if(titlePart != null)
            specification = specification.and(ProductSpecifications.titleLike(titlePart));

        return productRepository.findAll(specification, PageRequest.of(page - 1, 10));
    }

    public List<Product> getAllProductsToCompare(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto addNewProduct(ProductDto dto) {
        Product p = productRepository.save(productConverter.productDtoToProduct(dto));
        p.setId(null);
        return productConverter.productToProductDto(p);
    }

    public ProductDto updateProduct(ProductDto dto) {
        Product p = new Product();
        p.setCost(dto.getCost());
        p.setTitle(dto.getTitle());
        return productConverter.productToProductDto(productRepository.save(p));
    }

}
