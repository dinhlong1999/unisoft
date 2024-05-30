package com.example.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.common.CheckLogin;
import com.example.springmvc.service.AuthenticationService;
import com.example.springmvc.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

	@Autowired
	private IProductService productService;
	
	@Autowired
    private AuthenticationService authenticationService;
	
	@GetMapping("/getProductName")
	public String getProductNameFromProductCode(@RequestParam String productCode) {
		if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
			return "errorLogin";
	    }
		String productName = productService.getNameProductByCodeProduct(productCode);
		return productName;
	}
	
	@GetMapping("/getProductCode")
	public String getCodeProductFromNameProduct(@RequestParam String productName) {
		if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
			return "errorLogin";
	    }
		String productCode = productService.getCodeProductByNameProduct(productName);
		return productCode;
	}

}
