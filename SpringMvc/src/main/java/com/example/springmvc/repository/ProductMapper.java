package com.example.springmvc.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmvc.model.Product;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    List<Map<String, Object>> getListProduct( String productCode,String productName,int limit,int page);

    int totalCountGetListProduct( String productCode, String productName);

    int deleteProduct(int id,int version);
    
    int insertProduct(Product product);
    
    int getNameProductExists(String productName);
    
    int getCodeProductExists(String productCode);
    
    Product getProductById(int id);
    
    int updateProduct(Product product);
    
    int getNameProductExistsToUpdate(String productName,int id);
    
    int getCodeProductExistsToUpdate(String productCode,int id);
    
    String getCodeProductByNameProduct(String productName);
    
    String getNameProductByCodeProduct (String productCode);

    Product getProductByCodeProduct(String productCode);
}
