package com.kodlamaio.northwind.api.controllers;

import com.kodlamaio.northwind.business.abstracts.ProductService;
import com.kodlamaio.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
// Class that decides external requests (Controller)
public class ProductsController {

    private ProductService productService;

    //Bana (ProductService productService) lazım, projeyi tarıyor kim ProductService'ı implement etmiş  buluyor, ProductManger'ı bulduktan sonra bize ProductManger instance'ı oluşturmuş oluyor.
    @Autowired
    public ProductsController(ProductService productService) {
        super();
        this.productService = productService;
    }

    // .../api/products/getall
    @GetMapping("/getall")
    public List<Product> getAll() {
        return this.productService.getAll();
    }
}
