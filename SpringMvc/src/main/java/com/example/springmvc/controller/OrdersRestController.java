package com.example.springmvc.controller;

import com.example.springmvc.dto.OrdersDTO;
import com.example.springmvc.model.Account;
import com.example.springmvc.service.IAccountService;
import com.example.springmvc.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrdersRestController {

    @Autowired
    private IOrderService orderService;
    
    @Autowired
    private IAccountService accountService;
    
    
    private Account getAccountLogin () {
 	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 	   if (!(authentication instanceof AnonymousAuthenticationToken)) {
 			String usernameLogin = authentication.getName();
 			Account account  = accountService.getAccountByUsername(usernameLogin);
 			return account;
 		}
 	    	return null;
 	    }

    @PostMapping("/save")
    public Map<String,List<String>> saveOrder(@RequestBody List<OrdersDTO> data){
        Map<String,List<String>> errorsList  = new OrdersDTO().validate(data);
        if (errorsList.isEmpty()) {
        	 Account account = getAccountLogin();
        	int rowEffect = orderService.insertAndUpdateOrders(account.getId(), data);
		}
       
        return errorsList;
    }

}
