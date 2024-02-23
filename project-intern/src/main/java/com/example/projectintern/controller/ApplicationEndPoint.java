package com.example.projectintern.controller;

import com.example.projectintern.config.JwtRequestFilter;
import com.example.projectintern.dto.OrderDetailInfo;
import com.example.projectintern.model.Customer;
import com.example.projectintern.model.Product;
import com.example.projectintern.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import java.util.ArrayList;
import java.util.List;

@Endpoint
@Validated
public class ApplicationEndPoint {

    private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.vkakarla.com";

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IProductService productService;
    @Autowired
    private JwtRequestFilter requestFilter;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IOrderDetailService orderDetailService;



    public List<String> validateSaveOrder(OrderDetailInfo orderDetailInfo) {
        List<String> errorList = new ArrayList<>();
        Product productByCodeProduct = productService.findProductByCodeProduct(orderDetailInfo.getCodeProduct());
        Customer customerPhoneNumber = customerService.findCustomerByPhoneNumber(orderDetailInfo.getCustomerPhoneNumber());
        if (!orderDetailInfo.getCodeProduct().matches("^PR-\\d{4}$")) {
            errorList.add("Vui lòng nhập đúng mã sản phẩm");
        } else if (productByCodeProduct == null) {
            errorList.add("Không tồn tại sản phẩm");
        }
        if (orderDetailInfo.getQuantityBook() <= 0) {
            errorList.add("Số lượng đặt hàng không hợp lệ, vui lòng đặt lại");
        }
        if (customerPhoneNumber == null){
            errorList.add("Không tồn tại khách hàng trong hệ thống");
        }
        return errorList;

    }





//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateOrderRequest")
//    @ResponsePayload
//    public UpdateOrderResponse updateOrder(@RequestPayload UpdateOrderRequest request) {
//        ServiceStatus status = new ServiceStatus();
//        UpdateOrderResponse response = new UpdateOrderResponse();
//        List<String> errorList = validateUpdateOrder(request);
//        if (errorList.size() > 0) {
//            status.setError(errorList);
//            response.setServiceStatus(status);
//            return response;
//        }
//        Product product = productService.findProductByCodeProduct(request.getProductCode());
//        Customer customer = customerService.findCustomerByPhoneNumber(request.getPhoneNumberCustomer());
//        int orderUpdate = orderDetailService.updateOrder(LocalDate.parse(request.getDateStart()), request.getQuantityBook(), customer.getId(), request.getEmployeeId(), product.getId(), request.getPrice(), request.getOrderId());
//        if (orderUpdate == 1) {
//            status.setStatus("TRUE");
//            response.setServiceStatus(status);
//            return response;
//        } else {
//            status.setStatus("FALSE");
//            status.setMessage("Vui lòng thử lại");
//            return response;
//        }
//    }









}
