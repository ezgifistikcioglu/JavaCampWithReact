package com.kodlamaio.northwind.dataAccess.abstracts;

import com.kodlamaio.northwind.entities.concretes.Product;
import com.kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    Product getByProductName(String productName);

    Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);

    List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);

    List<Product> getByCategory_CategoryIdIn(List<Integer> categories);

    List<Product> getByProductNameContains(String productName);

    List<Product> getByProductNameStartsWith(String productName);

    @Query("From Product where productName =: productName and category.categoryId =: categoryId")
    List<Product> getByNameAndCategory(String productName, int categoryId);

    // select p.productID, p.productName, c.categoryName from Category c inner join Product p
    // on c.categoryId = p.categoryId

    // DB: select p.product_id, p.product_name, c.category_name from categories c inner join products p on c.category_id = p.category_id
    // select p.product_id,p.product_name, c.category_name,p.unit_price from products p inner join categories c on p.category_id =c.category_id

    @Query("Select new com.kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) From Category c Inner Join c.products p ")
    List<ProductWithCategoryDto> getProductWithCategoryDetails();
}
