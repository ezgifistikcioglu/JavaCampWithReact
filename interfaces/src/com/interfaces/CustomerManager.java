package com.interfaces;

public class CustomerManager {
    private Logger[] loggers;

    public CustomerManager(Logger[] loggers) {
        this.loggers = loggers;
    }

    public void add(Customer customer) {
        Utils.runLoggers(loggers, customer.getFirstName());
    }

    public void delete(Customer customer) {
        Utils.runLoggers(loggers, customer.getFirstName());
    }
}
