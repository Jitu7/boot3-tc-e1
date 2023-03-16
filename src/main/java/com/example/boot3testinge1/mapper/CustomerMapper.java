package com.example.boot3testinge1.mapper;

import com.example.boot3testinge1.model.Customer;
import com.example.boot3testinge1.model.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getCreatedAt());
    }
}
