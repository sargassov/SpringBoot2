package com.example.springboot1.repositories;

import com.example.springboot1.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostBetween(Integer min, Integer max);

//    @Query("select p from Product p where p.cost < :maxCost")
//    List<Product> findAllWhereCostLowerThan(Integer maxCost);
//
//    @Query("select p from Product p where p.cost > :minCost")
//    List<Product> findAllWhereCostUpperThan(Integer minCost);
}
