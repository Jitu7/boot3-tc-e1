package com.example.boot3testinge1.repository;

import com.example.boot3testinge1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
