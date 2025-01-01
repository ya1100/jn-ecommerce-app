package com.jn.ecommerce.customer.repository;

import com.jn.ecommerce.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findCustomerByEmail(String email);
}
