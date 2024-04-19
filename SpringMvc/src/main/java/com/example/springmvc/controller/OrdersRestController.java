package com.example.springmvc.controller;

import com.example.springmvc.dto.OrdersDTO;
import com.example.springmvc.service.ICustomerService;
import com.example.springmvc.service.IOrderService;
import com.example.springmvc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersRestController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    public String saveOrder(@RequestBody List<OrdersDTO> data){
        System.out.println(data.get(0));
        return "Dữ liệu gửi đi thành công" + data.size();
    }

}
