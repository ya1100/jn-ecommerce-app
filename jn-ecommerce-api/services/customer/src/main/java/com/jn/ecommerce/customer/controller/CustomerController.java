package com.jn.ecommerce.customer.controller;

import com.jn.ecommerce.config.AppConstants;
import com.jn.ecommerce.customer.dto.CustomerRequest;
import com.jn.ecommerce.customer.dto.CustomerResponse;
import com.jn.ecommerce.customer.service.CustomerService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody() CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.createCustomer(customerRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> getAllCustomers(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CUSTOMERS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        CustomerResponse customerResponse = customerService.getAllCustomers(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping("/exist/{customerId}")
    public ResponseEntity<Boolean> existCustomer(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(customerService.existCustomer(customerId), HttpStatus.OK);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customerId") String customerId) {
        CustomerResponse customerResponse = customerService.findById(customerId);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable() String customerId) {
        CustomerResponse customerResponse = customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

}
