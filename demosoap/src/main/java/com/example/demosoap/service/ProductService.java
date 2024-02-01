package com.example.demosoap.service;

import com.example.demosoap.model.Product;
import com.example.demosoap.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product getProductByID(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void deleteProductByID(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getListProduct() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
