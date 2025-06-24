package com.example.Inventory_management.entity;
import java.time.LocalDate;
import java.util.Date;
public class Product {
    private int id;
    private String name;
    private Category category;
    private double price;
    private int quantity;
    private LocalDate expiryDate;
    private Double discount; // null if no discount
    private boolean available = true; // default true

    public Product() {}

    public Product(int id, String name, Category category, double price, int quantity, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.discount = null;
        this.available = true;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getDiscountedPrice() {
        if (discount != null && discount > 0) {
            return price * (1 - discount);
        } else {
            return price;
        }
    }
}
