package com.coffeeApp.interfaces;

import com.coffeeApp.entities.Customer;

public interface CustomerService {
    void savedCustomer(Customer customer) throws Exception;
}
