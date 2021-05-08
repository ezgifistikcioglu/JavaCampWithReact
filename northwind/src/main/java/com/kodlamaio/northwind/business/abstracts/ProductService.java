package com.kodlamaio.northwind.business.abstracts;

import com.kodlamaio.northwind.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
}
