package com.example.springmvc.controller;

import com.example.springmvc.common.CheckLogin;
import com.example.springmvc.service.AuthenticationService;
import com.example.springmvc.service.ICustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;
    
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/getCustomerPhone")
    public String getPhoneNumber(@RequestParam String customerName ){
    	if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
			return "errorLogin";
	    }
    	String customerPhone = customerService.getPhoneNumberByNameCustomer(customerName);
        return customerPhone;
    }
    
    @GetMapping("/getCustomerName")
    public String getNameCustomer(@RequestParam String customerPhone) {
    	if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
			return "errorLogin";
	    }
    	String customerName = customerService.getNameByPhoneNumberCustomer(customerPhone);
    	return customerName;
    }
}
