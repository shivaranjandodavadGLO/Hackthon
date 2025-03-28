package com.hackathon.Products.controller;


import com.hackathon.Products.model.Products;
import com.hackathon.Products.service.ProductService;
import com.hackathon.Products.util.ProductConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    @PostMapping("/api/products/add")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        logger.info(ProductConstants.PRODUCT_RECEIVED, product);
        Products createdProduct = productService.addProduct(product);
        logger.info(ProductConstants.PRODUCT_CREATED_RESPONSE, createdProduct);
        return ResponseEntity.ok(createdProduct);
    }

    // Get all products
    @GetMapping("/api/products")
    public ResponseEntity<List<Products>> getAllProducts() {
        logger.info(ProductConstants.FETCHING_PRODUCTS);
        List<Products> products = productService.getAllProducts();
        logger.info(ProductConstants.TOTAL_PRODUCTS_FETCHED, products.size());
        return ResponseEntity.ok(products);
    }

    // Get product by ID
    @GetMapping("/api/products/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable String id) {
        logger.info(ProductConstants.PRODUCT_WITH_ID + id + " requested.");
        Optional<Products> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable String id, @RequestBody Products product) {
        try {
            logger.info("Received request to update product with ID: {}", id);
            Products updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            logger.error("Error updating product: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    // Delete product
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        logger.info("Deleting product with ID: {}", id);
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.error("Error deleting product: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }
}