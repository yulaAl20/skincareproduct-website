package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Add a new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Edit a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.editProduct(id, updatedProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.ok("Product with ID " + id + " has been deleted.");
        }
        return ResponseEntity.status(404).body("Product not found.");
    }

    // Find a product by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> findProductByName(@RequestParam String name) {
        List<Product> products = productService.findProductByName(name);
        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.status(404).body(null);
    }

    // Filter products by price range
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProductsByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Product> products = productService.filterProductsByPrice(minPrice, maxPrice);
        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.status(404).body(null);
    }
}
