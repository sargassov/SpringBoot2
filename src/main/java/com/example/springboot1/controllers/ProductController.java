package com.example.springboot1.controllers;

import com.example.springboot1.dto.ProductDto;
import com.example.springboot1.entities.Product;
import com.example.springboot1.exceptions.ProductNotFoundException;
import com.example.springboot1.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ){
        if(page < 1) page = 1;

        return productService.getAllProducts(page, minPrice, maxPrice, titlePart)
                .map(p -> new ProductDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto getProductsById(@PathVariable Long id) {

        return new ProductDto((productService.getProductById(id))
                .orElseThrow(() -> new ProductNotFoundException("Product #" + id + " not found")));

    }

    @PostMapping
    public ProductDto addProductToDatabase(@RequestBody ProductDto pDto){
        pDto.setId(null);
        return productService.addNewProduct(pDto);
    }

//    @PostMapping
//    public ProductDto addProductToCart(@RequestBody ProductDto pDto){
//        pDto.setId(null);
//        return productService.addNewProduct(pDto);
//    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto pDto){
        return productService.updateProduct(pDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }


}
