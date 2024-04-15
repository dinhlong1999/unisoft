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
	
	@GetMapping("/getNameProduct")
	public String getNameProductFromCodeProduct(@RequestParam String codeProduct) {
		String nameProduct = productService.getNameProductByCodeProduct(codeProduct);
		return nameProduct;
	}
	
	@GetMapping("/getCodeProduct")
	public String getCodeProductFromNameProduct(@RequestParam String nameProduct) {
		String codeProduct = productService.getCodeProductByNameProduct(nameProduct);
		return codeProduct;
	}
}
