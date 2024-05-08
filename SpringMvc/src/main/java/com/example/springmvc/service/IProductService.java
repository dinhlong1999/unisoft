package com.example.springmvc.service;

import com.example.springmvc.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getListProduct(String productCode, String productName, int limit, int offset);

    int totalRowGetListProduct(String productCode, String productName);
    
    int deleteProductById(int id, int version);
    
    int insertProduct(Product product);
    
    boolean isNameProductExists(String productName);

    boolean isCodeProductExists(String productCode);
    
    Product getProductById(int id);
    
    int updateProduct(Product product);
    
    boolean isNameProductExistsToUpdate(String productName, int id);
    
    boolean isCodeProductExistsToUpdate(String productCode, int id);
    
    String getCodeProductByNameProduct(String productName);
    
    String getNameProductByCodeProduct(String productCode);

    Product getProductByCodeProduct (String productCode);
}
