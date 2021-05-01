package com.inheritance2;

public class Main {

    public static void main(String[] args) {
        /*
         * Bad code example
         * LogManager logManager = new LogManager();
         * logManager.log(1);
         * logManager.log(2);
         * logManager.log(3);
         */

        CustomerManager customerManager = new CustomerManager();
        customerManager.add(new DatabaseLogger());
    }
}
