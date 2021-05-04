package com.coffeeApp.interfaces;


import com.coffeeApp.entities.Customer;

public interface CustomerCheckService {
    boolean checkIfRealPerson(Customer customer);
}
