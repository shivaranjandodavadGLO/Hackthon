package com.hackathon.Products.repositery;

import com.hackathon.Products.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositery extends JpaRepository<Products,String> {
}
