package com.example.springmvc.controller;

import com.example.springmvc.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/api/getPhoneNumberFromCustomerName")
    public String getPhoneNumber(@RequestParam String customerName ){
        String phoneNumber = customerService.getPhoneNumberByNameCustomer(customerName);
        return phoneNumber;
    }
}
