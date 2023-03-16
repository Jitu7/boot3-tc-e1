package com.example.boot3testinge1.integration.test;

import com.example.boot3testinge1.initializer.PostgresContainerInitializer;
import com.example.boot3testinge1.model.Customer;
import com.example.boot3testinge1.repository.CustomerRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {PostgresContainerInitializer.class})
class CustomerControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        customerRepository.deleteAll();
    }

    @Test
    void shouldGetAllCustomers() {
        List<Customer> customers = List.of(
                new Customer(null, "Jeetu", "jeetu@mail.com", Instant.now()),
                new Customer(null, "Mahadev", "mahadev@mail.com", Instant.now())
        );
        customerRepository.saveAll(customers);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/customers")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));

        List<Customer> customersList = given()
                .get("/api/customers")
                .jsonPath()
                .getList(".", Customer.class);
    }
}