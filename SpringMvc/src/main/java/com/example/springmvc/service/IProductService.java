package com.example.springmvc.service;

import com.example.springmvc.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getListProduct(String codeProduct, String nameProduct, int limit, int offset);

    int totalRowGetListProduct(String codeProduct, String nameProduct);
    
    int deleteProductById(int id);
    
    int insertProduct(Product product);
    
    boolean isNameProductExists(String nameProduct);

    boolean isCodeProductExists(String codeProduct);
    
    Product getProductById(int id);
    
    int updateProduct(Product product);
    
    boolean isNameProductExistsToUpdate(String nameProduct, int id);
    
    boolean isCodeProductExistsToUpdate(String codeProduct, int id);
}
