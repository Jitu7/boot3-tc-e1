package com.example.boot3testinge1.service;

import com.example.boot3testinge1.model.Product;
import com.example.boot3testinge1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll()
                .stream().filter(p -> !p.isDisabled()).toList();
    }
}