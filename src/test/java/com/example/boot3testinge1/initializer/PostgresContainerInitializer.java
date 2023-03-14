package com.example.boot3testinge1.initializer;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static PostgreSQLContainer postgresContainer = new PostgreSQLContainer("postgres:15.2-alpine");

    static {
        postgresContainer.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "spring.datasource.url=" + postgresContainer.getJdbcUrl(),
                "spring.datasource.username=" + postgresContainer.getUsername(),
                "spring.datasource.password=" + postgresContainer.getPassword()
        ).applyTo(applicationContext);
    }
}
