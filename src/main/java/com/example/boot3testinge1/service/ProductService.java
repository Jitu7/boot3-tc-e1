package com.example.boot3testinge1.service;

import com.example.boot3testinge1.model.Product;
import com.example.boot3testinge1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll()
                .stream().filter(p -> !p.isDisabled()).toList();
    }
}