package com.nLayeredDemo;

import com.nLayeredDemo.business.abstracts.ProductService;
import com.nLayeredDemo.business.concretes.ProductManager;
import com.nLayeredDemo.core.JLoggerManagerAdapter;
import com.nLayeredDemo.dataAccess.concretes.HeroProductDao;
import com.nLayeredDemo.entities.concretes.Product;

public class Main {

    public static void main(String[] args) {
        //TODO: It will be solved with Spring IoC.
        ProductService productService = new ProductManager(new HeroProductDao(), new JLoggerManagerAdapter());

        Product product = new Product(1,2,"Apple",12,50);
        productService.add(product);
    }
}
