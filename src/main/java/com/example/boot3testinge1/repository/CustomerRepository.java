package com.example.boot3testinge1.repository;

import com.example.boot3testinge1.model.Customer;
import com.example.boot3testinge1.model.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select new com.example.boot3testinge1.model.CustomerDTO(c.id, c.name, c.email, c.createdAt) from Customer c")
    Page<CustomerDTO> findCustomers(Pageable pageable);

    @Query("""
            select new com.example.boot3testinge1.model.CustomerDTO(c.id, c.name, c.email, c.createdAt) from Customer c
            where lower(c.name) like lower(concat('%', :query, '%'))
            """)
    Page<CustomerDTO> searchCustomers(String query, Pageable pageable);
}
