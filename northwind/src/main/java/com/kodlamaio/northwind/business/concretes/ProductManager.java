package com.kodlamaio.northwind.business.concretes;

import com.kodlamaio.northwind.business.abstracts.ProductService;
import com.kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import com.kodlamaio.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {
    private ProductDao productDao;

    @Autowired //construction injection
    public ProductManager(ProductDao productDao) {
        super();
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return this.productDao.findAll();
    }
}
