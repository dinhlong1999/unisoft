package com.example.springmvc.controller;

import com.example.springmvc.dto.OrdersDTO;
import com.example.springmvc.model.Product;
import com.example.springmvc.service.ICustomerService;
import com.example.springmvc.service.IOrderService;
import com.example.springmvc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IProductService productService;

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/save")
    public Map<String,List<String>> saveOrder(@RequestBody List<OrdersDTO> data){
        Map<String,List<String>> errorsList  = new OrdersDTO().validate(data);
        if (errorsList.isEmpty()) {
            for(OrdersDTO ordersDTO : data){
                if (ordersDTO.getId().matches("^\\d+$")){
                    int orderId = Integer.parseInt(ordersDTO.getId());
                    Product product = productService.getProductByCodeProduct(ordersDTO.getCodeProduct());
                    int customerId = customerService.getIdCustomerByPhoneNumber(ordersDTO.getPhoneNumber());
                    int versionOrders = orderService.getOrderVersionById(orderId);
                    int quantityBook = ordersDTO.getQuantityBook();
                    int updateOrder = orderService.updateOrder(customerId,product.getId(),quantityBook,versionOrders,orderId);
                    if (updateOrder != 1){
                        throw new RuntimeException("Không cap nhat được đối tượng");
                    }
                }


            }


		}
       
        return errorsList;
    }

}
