package com.jn.ecommerce.customer.service;

import com.jn.ecommerce.customer.dto.CustomerRequest;
import com.jn.ecommerce.customer.dto.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
     void updateCustomer(CustomerRequest customerRequest);
    CustomerResponse getAllCustomers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    Boolean existCustomer(String customerId);

    CustomerResponse findById(String customerId);

    CustomerResponse deleteCustomer(String customerId);
}
