package com.oopIntro;

public class Product {
    // Encapsulation
    // private: only accessible from this class

    private int id; //Think like a TC id number(it will be unique)
    private String name;
    private double unitPrice;
    private String detail;
    private double discount;

    // Created first constructor
    public Product() {

    }

    // Created second constructor
    public Product(int id, String name, double unitPrice, String detail, double discount) {
        this(); //invoke first constructor
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.detail = detail;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getUnitPriceAfterDiscount() {
        return unitPrice - (unitPrice * discount / 100);
    }

    public void setUnitPriceAfterDiscount(double unitPriceAfterDiscount) {

    }
}
