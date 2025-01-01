package com.jn.ecommerce.customer.service;

import com.jn.ecommerce.customer.dto.CustomerDTO;
import com.jn.ecommerce.customer.dto.CustomerRequest;
import com.jn.ecommerce.customer.dto.CustomerResponse;
import com.jn.ecommerce.customer.model.Customer;
import com.jn.ecommerce.customer.repository.CustomerRepository;
import com.jn.ecommerce.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(@Valid CustomerRequest customerRequest) {
        Customer newCustomer = modelMapper.map(customerRequest, Customer.class);
        Customer createdCustomer = customerRepository.save(newCustomer);
        return modelMapper.map(createdCustomer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerRequest.id()));
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse getAllCustomers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Customer> customerPage = customerRepository.findAll(pageDetails);

        List<Customer> customers = customerPage.getContent();

        // Convert the Customer entities to CustomerDTOs
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .toList();

        return new CustomerResponse(
                customerDTOs,
                customerPage.getNumber(),
                customerPage.getSize(),
                customerPage.getTotalElements(),
                customerPage.getTotalPages(),
                customerPage.isFirst(),
                customerPage.isLast()
        );
    }

    @Override
    public Boolean existCustomer(String customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public CustomerResponse findById(String customerId) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));
        return modelMapper.map(existingCustomer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse deleteCustomer(String customerId) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));
        customerRepository.deleteById(customerId);
        return modelMapper.map(existingCustomer, CustomerResponse.class);
    }
}
