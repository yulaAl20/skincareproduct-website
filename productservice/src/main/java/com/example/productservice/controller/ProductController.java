package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Add a new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Edit a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
            return ResponseEntity.ok(productRepository.save(product));
        }
        return ResponseEntity.status(404).body(null);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Product with ID " + id + " has been deleted.");
        }
        return ResponseEntity.status(404).body("Product not found.");
    }

    // Find a product by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> findProductByName(@RequestParam String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.status(404).body(null);
    }

    // Filter products by price range
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProductsByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.status(404).body(null);
    }
}
