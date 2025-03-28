package com.hackathon.Products.service;

import com.hackathon.Products.model.Products;
import com.hackathon.Products.repositery.ProductRepositery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService  {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepositery productRepository;

    @Override
    public List<Products> getAllProducts() {
        logger.info("Fetching all products...");
        List<Products> products = productRepository.findAll();
        logger.info("Total products found: {}", products.size());
        return products;
    }

    @Override
    public Optional<Products> getProductById(String productId) {
        logger.info("Fetching product with ID: {}", productId);
        Optional<Products> product = productRepository.findById(productId);
        if (product.isPresent()) {
            logger.info("Product found: {}", product.get());
        } else {
            logger.warn("Product with ID {} not found!", productId);
        }
        return product;
    }

    @Override
    public Products updateProduct(String productId, Products product) {
        logger.info("Updating product with ID: {}", productId);
        if (productRepository.existsById(productId)) {
            product.setProductId(productId);
            Products updatedProduct = productRepository.save(product);
            logger.info("Product updated successfully: {}", updatedProduct);
            return updatedProduct;
        }
        logger.error("Product with ID {} not found!", productId);
        throw new RuntimeException("Product not found with ID: " + productId);
    }

    @Override
    public Products addProduct(Products product) {
        // Generate a UUID if the product ID is null
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            String generatedId = UUID.randomUUID().toString();
            product.setProductId(generatedId);
            logger.info("Generated new Product ID: {}", generatedId);
        }

        logger.info("Creating new product: {}", product);
        Products savedProduct = productRepository.save(product);
        logger.info("Product created successfully with ID: {}", savedProduct.getProductId());

        return savedProduct;
    }


    @Override
    public void deleteProduct(String productId) {
        logger.info("Deleting product with ID: {}", productId);
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            logger.info("Product with ID {} deleted successfully.", productId);
        } else {
            logger.error("Product with ID {} not found!", productId);
            throw new RuntimeException("Product not found with ID: " + productId);
        }
    }
    }
