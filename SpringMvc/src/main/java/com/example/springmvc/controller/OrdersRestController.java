package com.example.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.common.CheckLogin;
import com.example.springmvc.dto.OrdersDTO;
import com.example.springmvc.model.Account;
import com.example.springmvc.service.AuthenticationService;
import com.example.springmvc.service.IOrderService;

@RestController
@RequestMapping("/api/orders")
public class OrdersRestController {

    @Autowired
    private IOrderService orderService;
    
    @Autowired
	private AuthenticationService authenticationService;
    
    @PostMapping("/save")
    public boolean saveOrder(@RequestBody List<OrdersDTO> data){
    	boolean result ;
        if (!new CheckLogin().isLogin(authenticationService.getAccountLogin())) {
			result = false;
	    }
        Account account = authenticationService.getAccountLogin();
        result = orderService.insertAndUpdateOrders(account.getId(), data);
        return result;
    }

}
