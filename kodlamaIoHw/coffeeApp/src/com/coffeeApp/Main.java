package com.coffeeApp;

import com.coffeeApp.abstracts.BaseCustomerManager;
import com.coffeeApp.adapters.MernisServiceAdapter;
import com.coffeeApp.concreate.StarbucksCustomerManager;
import com.coffeeApp.entities.Customer;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate birthdate = LocalDate.of(1955, 5, 5);

        Customer customer = new Customer("EZGİ", "FISTIKÇIOĞLU", birthdate, "17123465479");

        BaseCustomerManager baseCustomerManager = new StarbucksCustomerManager(new MernisServiceAdapter());
        try {
            baseCustomerManager.savedCustomer(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
