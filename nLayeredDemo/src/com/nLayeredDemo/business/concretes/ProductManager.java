package com.nLayeredDemo.business.concretes;

import com.nLayeredDemo.business.abstracts.ProductService;
import com.nLayeredDemo.core.LoggerService;
import com.nLayeredDemo.dataAccess.abstracts.ProductDao;
import com.nLayeredDemo.entities.concretes.Product;

import java.util.List;

public class ProductManager implements ProductService {
    private ProductDao productDao;
    private LoggerService loggerService;

    public ProductManager(ProductDao productDao, LoggerService loggerService) {
        this.productDao = productDao;
        this.loggerService = loggerService;
    }

    @Override
    public void add(Product product) {
        if (product.getCategoryId() == 1){
            System.out.println("No products are accepted in this category.");
            return;
        }
        this.productDao.add(product);
        this.loggerService.logToSystem("Product added, name : " + product.getName());
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }
}
