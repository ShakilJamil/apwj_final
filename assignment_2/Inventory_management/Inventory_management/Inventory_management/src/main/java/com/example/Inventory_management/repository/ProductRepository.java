package com.example.Inventory_management.repository;

import com.example.Inventory_management.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> productList = new ArrayList<>();

    public void save(Product product) {
        productList.add(product);
    }

    public Product findById(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> findAll() {
        return productList;
    }

    public void update(Product product) {
        Product existingProduct = findById(product.getId());
        if (existingProduct == null) return;

        productList.remove(existingProduct);
        productList.add(product);
    }

    public void delete(Product product) {
        Product existingProduct = findById(product.getId());
        if (existingProduct == null) return;

        productList.remove(existingProduct);
    }

    // New convenience method to delete by id
    public void deleteById(int id) {
        Product existingProduct = findById(id);
        if (existingProduct == null) return;
        productList.remove(existingProduct);
    }
}
