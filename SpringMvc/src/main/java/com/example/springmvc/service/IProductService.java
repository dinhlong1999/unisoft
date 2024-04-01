package com.example.springmvc.service;

import com.example.springmvc.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IProductService {
    List<Product> getListProduct(String codeProduct, String nameProduct, int limit, int offset);

    int totalRowGetListProduct(String codeProduct, String nameProduct);
    
    int deleteProductById(int id);
    
}
