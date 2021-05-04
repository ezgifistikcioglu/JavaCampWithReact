package com.coffeeApp.concreate;

import com.coffeeApp.entities.Customer;
import com.coffeeApp.interfaces.CustomerCheckService;

public class CustomerCheckManager implements CustomerCheckService {

    @Override
    public boolean checkIfRealPerson(Customer customer) {
        return true;
    }
}
