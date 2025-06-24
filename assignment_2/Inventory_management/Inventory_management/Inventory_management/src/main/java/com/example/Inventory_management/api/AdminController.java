package com.example.Inventory_management.api;

import com.example.Inventory_management.entity.Product;
import com.example.Inventory_management.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    private ProductService productService;


    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok("Product added");
    }

    @PostMapping("/products/bulk")
    public ResponseEntity<String> addProductsBulk(@RequestBody List<Product> products) {
        productService.addProductsBulk(products);
        return ResponseEntity.ok("Bulk products added");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return ResponseEntity.ok("Product updated");
    }

    @GetMapping("/products/expiring")
    public ResponseEntity<List<Product>> getExpiringProducts() {
        return ResponseEntity.ok(productService.getExpiringProducts());
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/reports/value")
    public ResponseEntity<Map<?, ?>> getTotalValueByCategory() {
        return ResponseEntity.ok(productService.getTotalValueByCategory());
    }

    @GetMapping("/reports/discounts")
    public ResponseEntity<Map<?, ?>> getProductsByCategoryWithDiscounts() {
        return ResponseEntity.ok(productService.getProductsByCategoryWithDiscounts());
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted.");
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


}
