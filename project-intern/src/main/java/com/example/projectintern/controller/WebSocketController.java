package com.example.projectintern.controller;

import com.example.projectintern.dto.ServiceStatus;
import com.example.projectintern.dto.orderDetail.SaveOrderDetailRequest;
import com.example.projectintern.dto.orderDetail.SaveOrderDetailResponse;
import com.example.projectintern.model.*;
import com.example.projectintern.service.IOrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class WebSocketController {

    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/order")
    public void saveOrder(SaveOrderDetailRequest request){
        OrderDetail orderDetail = new OrderDetail();
        Employee employee = new Employee();
        Product product = new Product();
        Customer customer = new Customer();
        Status status = new Status();
        SaveOrderDetailResponse response = new SaveOrderDetailResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        BeanUtils.copyProperties(request.getOrderDetail(),orderDetail);
        BeanUtils.copyProperties(request.getOrderDetail().getEmployee(),employee);
        BeanUtils.copyProperties(request.getOrderDetail().getProduct(),product);
        BeanUtils.copyProperties(request.getOrderDetail().getCustomer(),customer);
        BeanUtils.copyProperties(request.getOrderDetail().getStatus(),status);
        orderDetail.setEmployee(employee);
        orderDetail.setProduct(product);
        orderDetail.setCustomer(customer);
        orderDetail.setStatus(status);
        OrderDetail orderDetailCheck = orderDetailService.saveOrderDetail(orderDetail);
        if (orderDetailCheck != null){
            serviceStatus.setStatus("TRUE");
            serviceStatus.setMessage("HỢP LÝ");
            response.setServiceStatus(serviceStatus);
            messagingTemplate.convertAndSend("/topic/productUpdates", response);
        }else {
            serviceStatus.setStatus("FALSE");
            serviceStatus.setMessage("kHÔNG HỢP LÝ");
            response.setServiceStatus(serviceStatus);
            messagingTemplate.convertAndSend("/topic/productUpdates", response);
        }
    }

}
