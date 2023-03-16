package com.example.boot3testinge1.integration.test;

import com.example.boot3testinge1.initializer.PostgresContainerInitializer;
import com.example.boot3testinge1.model.Customer;
import com.example.boot3testinge1.repository.CustomerRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {PostgresContainerInitializer.class})
class CustomerControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        customerRepository.deleteAll();

        List<Customer> customers = List.of(
                new Customer(null, "Jeetu", "jeetu@mail.com", Instant.now()),
                new Customer(null, "Mahadev", "mahadev@mail.com", Instant.now()),
                new Customer(null, "Josh", "josh@mail.com", Instant.now()),
                new Customer(null, "Tarun", "tarun@mail.com", Instant.now()),
                new Customer(null, "Anil", "anil@mail.com", Instant.now()),
                new Customer(null, "Vijay", "vijay@mail.com", Instant.now()),
                new Customer(null, "Suhant", "suhant@mail.com", Instant.now()),
                new Customer(null, "Amit", "amit@mail.com", Instant.now()),
                new Customer(null, "Anmol", "anmol@mail.com", Instant.now()),
                new Customer(null, "Rohan", "rohan@mail.com", Instant.now())
        );
        customerRepository.saveAll(customers);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 10, 2, 1, true, false, true, false",
            "2, 10, 2, 2, false, true, false, true"
    })
    void shouldGetAllCustomers(int pageNo, int totalElements, int totalPages, int currentPage, boolean hasNext, boolean hasPrevious,
                               boolean isFirst, boolean isLast) {

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/customers?page=" + pageNo)
                .then()
                .statusCode(200)
                .assertThat()
                .body("totalElements", equalTo(totalElements))
                .body("totalPages", equalTo(totalPages))
                .body("currentPage", equalTo(currentPage))
                .body("hasNext", equalTo(hasNext))
                .body("hasPrevious", equalTo(hasPrevious))
                .body("isFirst", equalTo(isFirst))
                .body("isLast", equalTo(isLast))
        ;

    }

    @Test
    @Disabled
    void shouldGetAllCustomersDummy() {
        customerRepository.deleteAll();
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
                .assertThat()
                .body("totalElements", equalTo(2))
                .body("totalPages", equalTo(1))
                .body("currentPage", equalTo(1))
                .body("hasNext", equalTo(false))
                .body("hasPrevious", equalTo(false))
                .body("isFirst", equalTo(true))
                .body("isLast", equalTo(true))
        ;


        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/customers")
                .body()
                .equals("""
                        {
                            "data": [
                                {
                                    "id": 12,
                                    "name": "Jeetu",
                                    "email": "jeetu@mail.com",
                                    "createdAt": "2023-03-16T15:17:25.442399Z"
                                },
                                {
                                    "id": 13,
                                    "name": "Mahadev",
                                    "email": "mahadev@mail.com",
                                    "createdAt": "2023-03-16T15:17:25.442399Z"
                                }
                            ],
                            "totalElements": 2,
                            "totalPages": 1,
                            "currentPage": 1,
                            "hasNext": false,
                            "hasPrevious": false,
                            "isFirst": true,
                            "isLast": true
                        }
                        """);
    }
}