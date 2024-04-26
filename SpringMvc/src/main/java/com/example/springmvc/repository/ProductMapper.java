package com.example.springmvc.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmvc.model.Product;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    List<Map<String, Object>> getListProduct( String codeProduct,
            								 String nameProduct,
            								  int limit,
            								  int page);


    int totalCountGetListProduct( String codeProduct,
                                  String nameProduct);

    void deleteProduct(int id);
    
    int getDeleteProductCount(int id);
    
    int insertProduct(Product product);
    
    int getNameProductExists(String nameProduct);
    
    int getCodeProductExists(String codeProduct);
    
    Product getProductById(int id);
    
    int updateProduct(Product product);
    
    int getNameProductExistsToUpdate(String nameProduct,int id);
    
    int getCodeProductExistsToUpdate(String codeProduct,int id);
    
    String getCodeProductByNameProduct(String nameProduct);
    
    String getNameProductByCodeProduct (String codeProduct);

    Product getProductByCodeProduct(String codeProduct);
}
