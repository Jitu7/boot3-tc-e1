package com.example.boot3testinge1.controller;

import com.example.boot3testinge1.model.CreateCustomerRequest;
import com.example.boot3testinge1.model.CustomerDTO;
import com.example.boot3testinge1.model.CustomersDTO;
import com.example.boot3testinge1.service.CustomerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
@Tag(name = "Customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public CustomersDTO getAllCustomers(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                        @RequestParam(name = "query", defaultValue = "") String query) {
        if (StringUtils.hasLength(query)) {
            return customerService.searchCustomers(query, page);
        }
        return customerService.getAllCustomers(page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Successfully created new customer", responseCode = "201")
            }
    )
    public CustomerDTO createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        return customerService.createCustomer(request);
    }

}