package com.example.springmvc.service;

import com.example.springmvc.model.Product;
import com.example.springmvc.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Override
    public List<Product> getListProduct(String codeProduct, String nameProduct, int limit, int offset) {
        List<Map<String,Object>> productMap = productRepository.getListProduct(codeProduct,nameProduct,limit,offset);
        List<Product> productList = new ArrayList<>();
        for (Map<String,Object> map: productMap) {
            Product product = new Product();
            product.setId((int) map.get("id"));
            product.setCodeProduct((String) map.get("code_product"));
            product.setNameProduct((String) map.get("name_product"));
            product.setInventory((int) map.get("inventory"));
            product.setPriceBuy((double) map.get("price_buy"));
            product.setPriceSell((double) map.get("price_sell"));
           productList.add(product);

        }
        return productList;
    }

    @Override
    public int totalRowGetListProduct(String codeProduct, String nameProduct) {
        return productRepository.totalCountGetListProduct(codeProduct,nameProduct);
    }
}
