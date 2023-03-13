package com.example.boot3testinge1.repository;

import com.example.boot3testinge1.model.Product;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
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