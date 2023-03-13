package com.example.boot3testinge1.repository;

import com.example.boot3testinge1.model.Product;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15.2-alpine:///customers"
})
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAllActiveProducts() {
        entityManager.persist(new Product(null, "Cold Coffee", "Cold Bru Coffee", BigDecimal.TEN, false));
        entityManager.persist(new Product(null, "Cold Tea", "Cold Tata Tea", BigDecimal.TEN, true));

        List<Product> products = productRepository.findAllActiveProducts();

        assertThat(products).hasSize(1);
        assertThat(products.get(0).getId()).isNotEqualTo(null);
        assertThat(products.get(0).getName()).isEqualTo("Cold Coffee");
    }

}