package com.coffeeApp.entities;

import com.coffeeApp.interfaces.CustomerService;

import java.time.LocalDate;

public class Customer implements CustomerService {
    public String firstName;
    public String lastName;
    public String nationalityId;
    public LocalDate dateOfBirth;

    public Customer(String firstName, String lastName, LocalDate dateOfBirth, String nationalityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationalityId = nationalityId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public void savedCustomer(Customer customer) {

    }
}
