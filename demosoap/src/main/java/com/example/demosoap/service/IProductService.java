package com.example.demosoap.service;

import com.example.demosoap.model.Product;

import java.util.List;

public interface IProductService {

    Product getProductByID(int id);

    void deleteProductByID(int id);

    List<Product> getListProduct();

    void updateProduct(Product product);

}
