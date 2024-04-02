package com.example.springmvc.repository;

import org.apache.ibatis.annotations.Mapper;


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
}
