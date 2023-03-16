package com.example.boot3testinge1.controller;

import com.example.boot3testinge1.model.CustomersDTO;
import com.example.boot3testinge1.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public CustomersDTO getAllCustomers(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return customerService.getAllCustomers(page);
    }


}