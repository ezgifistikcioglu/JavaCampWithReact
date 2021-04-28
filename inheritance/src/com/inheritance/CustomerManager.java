package com.inheritance;

public class CustomerManager {
    public void add(Customer customer) {
        System.out.println(customer.customerNumber + " saved.");
    }

    public void addMultiple(Customer[] customers) {
        for (Customer customer : customers) {
            add(customer);
        }
    }
}
