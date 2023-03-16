package com.example.boot3testinge1.initializer;

import com.example.boot3testinge1.model.Customer;
import com.example.boot3testinge1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class CustomerDataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        customerRepository.deleteAllInBatch();
        customerRepository.save(new Customer(null, "Jeetu", "jeetu@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Josh", "josh@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Mahadev", "mahadev@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Tarun", "tarun@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Anil", "anil@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Vijay", "vijay@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Suhant", "suhant@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Sameer", "sameer@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Amit", "amit@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Anmol", "anmol@mail.com", Instant.now()));
        customerRepository.save(new Customer(null, "Rohan", "rohan@mail.com", Instant.now()));
    }
}
