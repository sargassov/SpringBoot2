package com.example.springboot1.carts;

import com.example.springboot1.dto.ProductCartDto;
import com.example.springboot1.dto.ProductDto;
import com.example.springboot1.exceptions.ProductNotFoundException;
import com.example.springboot1.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Cart {
    private List<ProductCartDto> selectedProducts;

    public Cart() {
        this.selectedProducts = new ArrayList<>();
    }

    public void add(ProductCartDto pco){

        ProductCartDto productCartDto = selectedProducts.stream()
                .filter(p -> p.getId().equals(pco.getId())).findFirst()
                .orElse((new ProductCartDto(pco)));

        if(selectedProducts.contains(productCartDto)){
            selectedProducts.set(selectedProducts.indexOf(productCartDto), elementAddQuantity(productCartDto));
            return;
        }

        selectedProducts.add(elementAddQuantity(productCartDto));

//        Optional<CartElement> element = selectedProducts.stream()
//                .filter(e -> e.getProductDto().getId().equals(pDto.getId())).findFirst();
//
//        if(element.isPresent()){
//            int counter = selectedProducts.indexOf(element);
//            CartElement el = selectedProducts.get(counter);
//            el.setQuantity(el.getQuantity() + 1);
//            purchasePrice += el.getProductDto().getCost();
//            return;
//        }
//
//        selectedProducts.add(new CartElement(pDto, 1));
    }

    private ProductCartDto elementAddQuantity(ProductCartDto productCartDto) {
        productCartDto.setQuantity(productCartDto.getQuantity() + 1);
        return productCartDto;
    }


    public List<ProductCartDto> getAll() {
        return Collections.unmodifiableList(selectedProducts);
    }

    public void removeAll() {
        selectedProducts.clear();
    }

    public void removeOneWhereIdLike(Long id) {
        selectedProducts.remove(
                selectedProducts.stream().filter(p -> p.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product #" + id + " not found")));
    }

    public int checkCartQuantity(){
        int totalQuantity = 0;
        for(ProductCartDto p : selectedProducts) totalQuantity += p.getQuantity();
        return totalQuantity;
    }
}
