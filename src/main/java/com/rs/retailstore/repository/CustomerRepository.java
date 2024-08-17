package com.rs.retailstore.repository;

import com.rs.retailstore.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    List<Customer> findByUsername(String username);
}
