package com.rs.retailstore.controller;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class RegisterController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        ResponseEntity<String> response = null;

        try {
           Customer saveCustomer = customerService.createCustomer(customer);
           if (saveCustomer.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Customer registered successfully");
           }
        }catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error" + e);
        }

        return response;
    }
}
