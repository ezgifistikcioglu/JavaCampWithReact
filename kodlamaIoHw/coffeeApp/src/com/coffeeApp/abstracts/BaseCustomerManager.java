package com.coffeeApp.abstracts;

import com.coffeeApp.entities.Customer;
import com.coffeeApp.interfaces.CustomerService;

public abstract class BaseCustomerManager implements CustomerService {
    @Override
    public void savedCustomer(Customer customer) throws Exception {
        System.out.println("Saved to database : " + customer.getFirstName());
    }
}
