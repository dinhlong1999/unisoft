package com.example.springmvc.service;

import com.example.springmvc.model.Product;

import com.example.springmvc.repository.ProductMapper;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    
    @Override
    public List<Product> getListProduct(String codeProduct, String nameProduct, int limit, int offset) {
        List<Map<String,Object>> productMap = productMapper.getListProduct(codeProduct,nameProduct,limit,offset);
        List<Product> productList = new ArrayList<>();
        for (Map<String,Object> map: productMap) {
            Product product = new Product();
            product.setId((int) map.get("id"));
            product.setCodeProduct((String) map.get("codeProduct"));
            product.setNameProduct((String) map.get("nameProduct"));
            product.setInventory((int) map.get("inventory"));
            product.setPriceBuy((double) map.get("priceBuy"));
            product.setPriceSell((double) map.get("priceSell"));
           productList.add(product);

        }
        return productList;
    }

    @Override
    public int totalRowGetListProduct(String codeProduct, String nameProduct) {
        return productMapper.totalCountGetListProduct(codeProduct,nameProduct);
    }
    
    @Transactional
	@Override
	public int deleteProductById(int id) {
		  productMapper.deleteProduct(id);
	 int result = productMapper.getDeleteProductCount(id);
	 return result;
	}

	@Override
	public int insertProduct(Product product) {
		return productMapper.insertProduct(product);
	}

    @Override
	public boolean isCodeProductExists(String codeProduct) {
		int count = productMapper.getCodeProductExists(codeProduct);
        return count == 0;
    }
	
	public boolean isNameProductExists(String nameProduct) {
		int count = productMapper.getNameProductExists(nameProduct);
		return count == 0;
	}

	@Override
	public Product getProductById(int id) {
		return productMapper.getProductById(id) ;
	}

	@Override
	public int updateProduct(Product product) {
		return productMapper.updateProduct(product);
	}

	@Override
	public boolean isNameProductExistsToUpdate(String nameProduct, int id) {
		int result = productMapper.getNameProductExistsToUpdate(nameProduct, id);
		return result == 0;
	}

	@Override
	public boolean isCodeProductExistsToUpdate(String codeProduct, int id) {
		int result = productMapper.getCodeProductExistsToUpdate(codeProduct, id);
		return result == 0;
	}
	
	

	
}
