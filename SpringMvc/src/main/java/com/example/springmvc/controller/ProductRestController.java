package com.example.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

	@Autowired
	private IProductService productService;
	
	@GetMapping("/getProductName")
	public String getProductNameFromProductCode(@RequestParam String productCode) {
		String productName = productService.getNameProductByCodeProduct(productCode);
		return productName;
	}
	
	@GetMapping("/getProductCode")
	public String getCodeProductFromNameProduct(@RequestParam String productName) {
		String productCode = productService.getCodeProductByNameProduct(productName);
		return productCode;
	}

}
