package com.example.Inventory_management.service;

import com.example.Inventory_management.entity.Category;
import com.example.Inventory_management.entity.Product;
import com.example.Inventory_management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl  implements ProductService {

    private ProductRepository productRepository;

    private static final double DISCOUNT_RATE = 0.2;

    // Ideally use constructor injection
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void addProductsBulk(List<Product> products) {
        for (Product product : products) {
            addProduct(product);
        }
    }

    @Override
    public void updateProduct(Product product) {
        Product existing = productRepository.findById(product.getId());
        if (existing == null) return;

        productRepository.update(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getExpiringProducts() {
        LocalDate today = LocalDate.now();
        LocalDate next7days = today.plusDays(7);

        List<Product> expiringProducts = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (product.getExpiryDate() == null) {
                product.setDiscount(null);
                product.setAvailable(true);
                continue;
            }

            if (product.getExpiryDate().isBefore(today)) {
                product.setAvailable(false);
                product.setDiscount(null);
            } else if (!product.getExpiryDate().isAfter(next7days)) {
                product.setDiscount(DISCOUNT_RATE);
                product.setAvailable(true);
                expiringProducts.add(product);
            } else {
                product.setDiscount(null);
                product.setAvailable(true);
            }
        }
        return expiringProducts;
    }

    @Override
    public List<Product> getAllProducts() {
        getExpiringProducts();
        return new ArrayList<>(productRepository.findAll());
    }

    @Override
    public Map<Category, Double> getTotalValueByCategory() {
        Map<Category, Double> totalValueMap = new HashMap<>();
        for (Product product : productRepository.findAll()) {
            if (product.isAvailable()) {
                double value = product.getPrice() * product.getQuantity();
                totalValueMap.merge(product.getCategory(), value, Double::sum);
            }
        }
        return totalValueMap;
    }

    @Override
    public Map<Category, List<Product>> getProductsByCategoryWithDiscounts() {
        Map<Category, List<Product>> map = new HashMap<>();
        for (Product product : productRepository.findAll()) {
            map.computeIfAbsent(product.getCategory(), k -> new ArrayList<>()).add(product);
        }
        return map;
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

}
