package com.example.Inventory_management.service;

import com.example.Inventory_management.entity.Category;
import com.example.Inventory_management.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {


    void addProduct(Product product);


    void addProductsBulk(List<Product> products);


    void updateProduct(Product product);


    void delete(int product);

     Product findById(int id);


    List<Product> getExpiringProducts();


    List<Product> getAllProducts();


    Map<Category, Double> getTotalValueByCategory();


    Map<Category, List<Product>> getProductsByCategoryWithDiscounts();

}
