package com.hackathon.Products.service;

import com.hackathon.Products.model.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Products> getAllProducts();

    Optional<Products> getProductById(String productId);

    Products updateProduct(String productId, Products product);

    Products addProduct(Products product);

    void deleteProduct(String productId);
}
