package com.interfaces;

public class Main {

    public static void main(String[] args) {
        Logger[] loggers = {new FileLogger(), new DatabaseLogger(), new SmsLogger(), new EmailLogger()};

        CustomerManager customerManager = new CustomerManager(loggers);

        Customer customer = new Customer(1,"somebody's name","somebody's surname");

        customerManager.add(customer);

    }
}
