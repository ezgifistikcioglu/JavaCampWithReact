package com.oopIntro;

public class Main {

    public static void main(String[] args) {
        Product product1 = new Product(1, "Lenovo V14", 15000, "16GB RAM"); //created reference(instance)
        /*
         * Before using a constructor
            product1.id = 1;
            product1.name = "Lenovo V14";
            product1.unitPrice = 15000;
            product1.detail = "16GB RAM";
         */

        Product product2 = new Product(2, "Lenovo V15", 16000, "32GB RAM");

        Product product3 = new Product(3, "HP 5", 10000, "8GB RAM");

        //initialize Product ArrayList
        Product[] products = {product1, product2, product3};
        //foreach loop
        for (Product product : products) {
            System.out.println(product.name);
        }
        ;
        System.out.println((products.length));

        //Created Category reference
        Category category1 = new Category(1,"Bilgisayar");
        /*
         * Before using a constructor
           category1.id = 1;
           category1.name = "Bilgisayar";
         */

        Category category2 = new Category(2,"Ev/Bahçe");


        //invoke ProductManager class.
        ProductManager productManager = new ProductManager();
        productManager.addToCart(product1);
        productManager.addToCart(product2);
        productManager.addToCart(product3);

    }
}
