package com.nLayeredDemo.dataAccess.concretes;

import com.nLayeredDemo.dataAccess.abstracts.ProductDao;
import com.nLayeredDemo.entities.concretes.Product;

import java.util.List;

public class HeroProductDao implements ProductDao {
    @Override
    public void add(Product product) {
        System.out.println("Added with Hero : " + product.getName());
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }
}
