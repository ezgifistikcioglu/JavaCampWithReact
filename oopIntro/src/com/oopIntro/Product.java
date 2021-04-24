package com.oopIntro;

public class Product {
    int id; //Think like a TC id number(it will be unique)
    String name;
    double unitPrice;
    String detail;

    // Created first constructor
    public Product(){
        System.out.println("I worked");
    }
    // Created second constructor
    public Product(int id, String name, double unitPrice, String detail) {
        this(); //invoke first constructor
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.detail = detail;
    }
}
