package com.example.boot3testinge1.controller;

import com.example.boot3testinge1.model.Product;
import com.example.boot3testinge1.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return active products")
    void getAllProducts() throws Exception {

        var coffee = new Product(1L, "Coffee", "Nescafe", BigDecimal.TEN, false);
        var tea = new Product(1L, "Tea", "Tata", BigDecimal.TEN, false);
        given(productService.getAllProducts()).willReturn(List.of(coffee, tea));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].name").value("Coffee"))
                .andExpect(jsonPath("$.[1].name").value("Tea"));

    }
}