package com.example.springmvc.service;

import com.example.springmvc.model.Product;
import com.example.springmvc.repository.ProductMapper;

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
	public Product getProductByCodeProduct(String productCode) {
		return productMapper.getProductByCodeProduct(productCode);
	}
    
    @Override
    public List<Product> getListProduct(String productCode, String productName, int limit, int offset) {
        List<Map<String,Object>> productMap = productMapper.getListProduct(productCode,productName,limit,offset);
        List<Product> productList = new ArrayList<>();
        for (Map<String,Object> map: productMap) {
            Product product = new Product();
            product.setId((int) map.get("id"));
            product.setCode((String) map.get("code"));
            product.setName((String) map.get("name"));
            product.setVersion((int) map.get("version"));
            product.setInventory((int) map.get("inventory"));
            product.setPriceBuy((double) map.get("price_buy"));
            product.setPriceSell((double) map.get("price_sell"));
           productList.add(product);

        }
        return productList;
    }

    @Override
    public int totalRowGetListProduct(String productCode, String productName) {
        return productMapper.totalCountGetListProduct(productCode,productName);
    }
    
    
	@Override
	public int deleteProductById(int id, int version) {
		return productMapper.deleteProduct(id,version) ;
	}

	@Override
	public int insertProduct(Product product) {
		return productMapper.insertProduct(product);
	}

    @Override
	public boolean isCodeProductExists(String productCode) {
		int count = productMapper.getCodeProductExists(productCode);
        return count == 0;
    }
	
	public boolean isNameProductExists(String productName) {
		int count = productMapper.getNameProductExists(productName);
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
	public boolean isNameProductExistsToUpdate(String productName, int id) {
		int result = productMapper.getNameProductExistsToUpdate(productName, id);
		return result == 0;
	}

	@Override
	public boolean isCodeProductExistsToUpdate(String productCode, int id) {
		int result = productMapper.getCodeProductExistsToUpdate(productCode, id);
		return result == 0;
	}

	@Override
	public String getCodeProductByNameProduct(String productName) {
		return productMapper.getCodeProductByNameProduct(productName);
	}

	@Override
	public String getNameProductByCodeProduct(String productCode) {
		return productMapper.getNameProductByCodeProduct(productCode);
	}
	
	

	
}
