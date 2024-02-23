package com.example.projectintern.service;

import com.example.projectintern.dto.IProductDetail;
import com.example.projectintern.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<IProductDetail> getAllProduct(Pageable pageable, String codeProduct, String nameProduct);

    void saveProduct(Product product);

    Product findProductByCodeProduct(String codeProduct);

    Product findProductByNameProduct(String nameProduct);

    Product findProductById(int id);

    List<Product> findAll();

    int getTotalRecordProduct(String codeProduct, String nameProduct);
    List<IProductDetail>  getAllProductByNameProductAndCodeProduct(String codeProduct, String nameProduct,
                                                                   int limitNumber, int page);
    int updateProduct(Product product);
}
