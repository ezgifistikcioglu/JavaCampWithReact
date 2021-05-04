package com.coffeeApp.concreate;

import com.coffeeApp.abstracts.BaseCustomerManager;
import com.coffeeApp.entities.Customer;
import com.coffeeApp.interfaces.CustomerCheckService;

public class StarbucksCustomerManager extends BaseCustomerManager {
    private final CustomerCheckService customerCheckService;

    public StarbucksCustomerManager(CustomerCheckService customerCheckService) {
        this.customerCheckService = customerCheckService;
    }

    @Override
    public void savedCustomer(Customer customer) throws Exception {
        if (customerCheckService.checkIfRealPerson(customer)){
            super.savedCustomer(customer);
        }else {
            System.out.println(customer.firstName + " " + customer.lastName + " - " + customer.dateOfBirth + " - " +  "TC: "+ customer.nationalityId + " => !!Not Saved to database" );
            throw new Exception("Not a valid person");
        }
    }
}
