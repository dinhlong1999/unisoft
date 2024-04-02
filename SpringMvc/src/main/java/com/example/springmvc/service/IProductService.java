package com.example.springmvc.service;

import com.example.springmvc.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getListProduct(String codeProduct, String nameProduct, int limit, int offset);

    int totalRowGetListProduct(String codeProduct, String nameProduct);
    
    int deleteProductById(int id);
    
}
