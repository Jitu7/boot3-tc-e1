package com.example.boot3testinge1.repository;

import com.example.boot3testinge1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.disabled=false ")
    List<Product> findAllActiveProducts();
}