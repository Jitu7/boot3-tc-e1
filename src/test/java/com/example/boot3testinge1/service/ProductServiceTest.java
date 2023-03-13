package com.example.boot3testinge1.service;

import com.example.boot3testinge1.model.Product;
import com.example.boot3testinge1.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class ProductServiceTest {

    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    @DisplayName("Should return only active products")
    void shouldReturnOnlyActiveProducts() {
        // Arrange
        var ssBat = new Product(1L, "SS", "Bat", BigDecimal.valueOf(5000), false);
        var rbkBat = new Product(2L, "RBK", "Bat", BigDecimal.valueOf(1000), true);
        given(productRepository.findAll()).willReturn(List.of(ssBat, rbkBat));

        // Act
        List<Product> products = productService.getAllProducts();

        // Assert
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getId()).isEqualTo(ssBat.getId());
        assertThat(products.get(0).getName()).isEqualTo(ssBat.getName());

    }
}