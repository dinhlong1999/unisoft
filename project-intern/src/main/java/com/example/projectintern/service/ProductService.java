package com.example.projectintern.service;

import com.example.projectintern.dto.IProductDetail;
import com.example.projectintern.model.Product;
import com.example.projectintern.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<IProductDetail> getAllProduct(Pageable pageable, String codeProduct, String nameProduct) {
        return productRepository.getAllProduct(pageable,"%"+codeProduct+"%","%"+nameProduct+"%");
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productRepository.saveProduct(product.getCodeProduct(), product.getNameProduct(), product.getPriceBuy(),product.getPriceSell());
    }

    @Override
    public Product findProductByCodeProduct(String codeProduct) {
        return productRepository.findProductByCodeProduct(codeProduct);
    }

    @Override
    public Product findProductByNameProduct(String nameProduct) {
        return productRepository.findProductByNameProduct(nameProduct);
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public int getTotalRecordProduct(String codeProduct, String nameProduct) {
        return productRepository.getTotalRecordProduct("%"+ codeProduct + "%","%"+ nameProduct + "%");
    }

    @Override
    public List<IProductDetail> getAllProductByNameProductAndCodeProduct(String codeProduct, String nameProduct, int limitNumber, int page) {
        return productRepository.getAllProductByNameProductAndCodeProduct("%"+ codeProduct + "%","%"+ nameProduct + "%",limitNumber,page);
    }

    @Override
    @Transactional
    public int updateProduct(Product product) {
        return productRepository.updateProduct(product.getCodeProduct(), product.getNameProduct(), product.getPriceBuy(),
                product.getPriceSell(),product.getId(),product.getVersion());
    }
}
