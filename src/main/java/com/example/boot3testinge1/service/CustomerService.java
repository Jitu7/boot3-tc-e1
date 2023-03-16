package com.example.boot3testinge1.service;

import com.example.boot3testinge1.mapper.CustomerMapper;
import com.example.boot3testinge1.model.CreateCustomerRequest;
import com.example.boot3testinge1.model.Customer;
import com.example.boot3testinge1.model.CustomerDTO;
import com.example.boot3testinge1.model.CustomersDTO;
import com.example.boot3testinge1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public CustomersDTO getAllCustomers(Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.Direction.DESC, "createdAt");
        Page<CustomerDTO> customerPage = customerRepository.findCustomers(pageable);
        return new CustomersDTO(customerPage);
    }

    @Transactional(readOnly = true)
    public CustomersDTO searchCustomers(String query, Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.Direction.DESC, "createdAt");
        Page<CustomerDTO> customerPage = customerRepository.searchCustomers(query, pageable);
        return new CustomersDTO(customerPage);
    }

    public CustomerDTO createCustomer(CreateCustomerRequest request) {
        var customer = new Customer(null, request.getName(), request.getEmail(), Instant.now());
        var savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }
}
