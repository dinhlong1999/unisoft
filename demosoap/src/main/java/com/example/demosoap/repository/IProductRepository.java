package com.example.demosoap.repository;

import com.example.demosoap.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Integer> {
    Product findProductById(int id);
}
